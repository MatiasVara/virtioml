# Virtioml


## Introduction

- VIRTIO Modeling Language is a set of tools to model the virtIO specification. 

- You can find more information about the specification in the following link https://docs.oasis-open.org/virtio/virtio/v1.1/cs01/virtio-v1.1-cs01.html

- The core of these tools is the metamodel of the virtqueue. This metamodel is used to build a metamodel to describes virtio-devices and a metamodel to describes virtio-drivers. These two metamodels import the virtqueue metamodel. 

- By relying on these metamodels, it is possible to model both the virtio-device and the virtio-driver. 

- To do so, a model conforming to the corresponding metamodel must be instantiated. 

- For example, the virtio-ballon project includes the model of the device and a model of the driver. 

- The repository also includes a simple model-to-text transformation that implements a generator of c code. This generator generates the headers of virtioqueue in the c language. 

- To illustrate the use of these projects, the repository includes the model of the virtio balloon device and driver. 

- In this README, we first present the metamodel: the virtqueue metamodel, the virtio-device metamodel and the virtio-driver metamodel. 

- To illustrate the use of these metamodel, we present the model of the virtio-balloon device and driver. 

- We finish with simple steps to try the example and we present the fugure work.  

  

## VirtIO Queue Model

- The virtqueue metamodel contains the concepts and the relationships between the concepts which are present in the virtqueue. 

- For example, a virtqueue contains virtq_desc, virtq_availabe and virque_used

![virtioqueue](./plugins/org.virtio.model.virtioqueue/model/virtioqueue.jpg)

## Header Generator

- From this metamodel, it is possible to generate the header in c. 

- The classes WORD, DWORD, QWORD and Byte are not defined here and must be defined manually in the header.

## VirtIO Device Model

It describes the different registers that a virtio device has. These methods are in charge to modify the Device Status field. The .ecl describes the correct order in which the device status must be modified. 

![virtiodevice](./plugins/org.virtio.model.virtiodevice/model/virtiodevice.jpg)

## VirtIO Driver Model

![virtiodriver](./plugins/org.virtio.model.virtiodriver/model/virtiodriver.jpg)

## Example: Virtio-balloon

- To illustrate the use of this models, the repo includes the models of the virtio-balloon device and driver. It is possible to execute these models to verify the correct interaction between both. 
- Both models are built independent. 
- The validation is perform during execution. 
- Explicar como funciona el ballon y como fue el workflow que usaste
- one model of the device
- one model of the driver
- the device has references to virtqueue but the driver has to instantiates during initialization
- the driver trigger the initialization procedure by setting the device status and then seting up the virtqueues.

## How to Try it

GEMOC

## Future Work

- Java description of the behavior. 
- Formal description of the behavior
- Automatic generation of the implementation of the driver and the device