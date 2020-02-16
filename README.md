# Virtioml


## Introduction

VIRTIO Modeling Language (VirtioML) is a set of tools to model the VirtIO specification, which is a common interface for virtual devices and drivers. You can find more information about the specification at https://docs.oasis-open.org/virtio/virtio/v1.1/cs01/virtio-v1.1-cs01.htm. 

The VirtIO specification relies on the notion of **virtqueues** which are the memory structures used by devices and drivers to interact. For example, a virtio network card device has a virtque to transmits packet to the network. The guest enqueues packets into the virtqueue and the host dequeues packets from this virtqueue.  

To represent the concept of virtqueue, we propose the use of a metamodel. From this metamodel, it is possible to generates the headers in c. To describe virtio-devices and virtio-drivers, the virtqueue metamodel is used to build the virtio-device metamodel and the virtio-driver metamodel. By relying on these metamodels, it is possible to model virtio-devices and virtio-drivers. To illustrate the use of these metamodels, we include the modeling of the **virtio-ballon** device and driver.

The current work is exploratory! It is still not clear the benefits and drawbacks of the use of modeling tools for VirtIO. The idea is that the use of modeling tools for the VirtIO specification may allow:

- Generate documentation, e.g., Latex
- Generate headers, e.g., .h
- Generate source code sketch for drivers and devices
- Generate monitors that check if a device conforms the specification
- Describe formally the behavior to allow simulation and verification

In the following, we elaborate on the virtqueue metamodel, the virtio-device metamodel and the virtio-driver metamodel. At the end of the document, you can find how to try it and contribute to the project.  

## VirtIO Queue Metamodel

The virtqueue metamodel contains concepts and relationships to describe virtqueues. Figure 1 illustrates the virtiqueue metamodel. The metamodel specifies that a **virtq** metaclass contains references to the metaclasses: **virtq_desc**, **virtq_availabe** and **virque_used**.

![virtqueue](./plugins/org.virtio.model.virtioqueue/model/virtioqueue.jpg "Virtqueue Metamodel")

## Header Generator

This metamodel can be used to generate the header in c. For example, the **virtq** metaclass is translated to the c structure:

```c
struct virtq {
    struct virtq_avail *avail;
    struct virtq_used  *used;
    struct virtq_desc  *desc;
}
```

The transformation translates references to pointers. The generated file can be found at [header.h](https://github.com/MatiasVara/virtioml/blob/master/plugins/org.virtio.model.virtioqueue/headers/virtio-queue.h). The metaclasses **Word**, **DWord**, **QWord** and **Byte** must be manually defined. The generation is implemented by using a model-to-text transformation in Acceleo. 

## VirtIO Device Metamodel

The virtio-device metamodel contains the concepts and relationships to describe virtio-devices. Figure 2 illustrates the metamodel. The **virtiodevice** metaclass is the core of the description. It contains an **Id** that identifies the virtio-device. **QueuesNr** contains the number of queues the device exposes to the driver. It also contains a set of methods that changes the value of the **DeviceStatus** register. These methods are usually invoked during the initialization.

Based on the VirtIO specification, these methods must be executed in a particular order to ensure the correct initialization of the device. For example, the following text is part of the steps that the driver has to follow to initialize a device: 

```
The driver MUST follow this sequence to initialize a device:

1) Reset the device.
2) Set the ACKNOWLEDGE status bit: the guest OS has noticed the device.
...
```

This statement can be translated into a temporal relationship between the methods Reset() and Ack(). For example, if we encode this relationship by using the formal language CCSL, the statement is translated to the following code:

```
context virtiodevice
   inv ackafterreset : 
     Relation Precedes(self.Reset, self.Ack) 
```

In this code, the **ackafterreset** is an invariant that specifies that the method Reset() must be invoked before the method Ack(). From this specification, it possible to generate c monitors to check that a device is correctly initialized. 


![virtiodevice](./plugins/org.virtio.model.virtiodevice/model/virtiodevice.jpg)

## VirtIO Driver Metamodel

- The virtio-driver metamodel contains the concepts and the relatioonships to model virtio-drivers.
- The root element is the virtio-driver class that refers to a virtio-device  class.
- This class is imported from the virtio-device metamodel.  

![virtiodriver](./plugins/org.virtio.model.virtiodriver/model/virtiodriver.jpg)

## Example: Virtio-balloon

- To illustrate the use of this models, the repo includes the models of the virtio-balloon device and driver. It is possible to execute these models to verify the correct interaction between both. 
- Both models are built independent. 
- The validation is perform during execution. 
- To explain how the virtio-ballon work and the used workflow.
- one model of the device
- one model of the driver
- the device has references to virtqueue but the driver has to instantiates during initialization
- the driver trigger the initialization procedure by setting the device status and then seting up the virtqueues.

## How to Try it

- Download the GEMOC studio
- Clone the repos. 

## How to Contribute

- Create an issue!
