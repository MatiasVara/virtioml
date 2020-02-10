package org.virtio.balloon.execution;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.virtio.model.virtiodevice.VirtiodevicePackage;
import org.virtio.model.virtiodevice.virtiodevice;
import org.virtio.model.virtiodriver.*;

public class BalloonExecution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VirtiodevicePackage.eINSTANCE.eClass();

        // Register the XMI resource factory for the .website extension

        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("virtiodevice", new XMIResourceFactoryImpl());

        // Obtain a new resource set
        ResourceSet resSet = new ResourceSetImpl();

        // Get the resource
        Resource resource = resSet.getResource(URI.createFileURI("device/balloon.virtiodevice"), true);
        // Get the first model element and cast it to the right type, in my
        // example everything is hierarchical included in this first node
        virtiodevice mydevice = (virtiodevice) resource.getContents().get(0);
        System.out.println("Model of device: " + mydevice.getName() + " loaded");
        

		VirtiodriverPackage.eINSTANCE.eClass();
        m.put("virtiodriver", new XMIResourceFactoryImpl());
        // Get the resource
        Resource resourcedriver = resSet.getResource(URI.createFileURI("driver/balloon.virtiodriver"), true);       
        // Get the first model element and cast it to the right type, in my
        // example everything is hierarchical included in this first node
        virtiodriver mydriver = (virtiodriver) resourcedriver.getContents().get(0);
        System.out.println("Model of driver: " + mydriver.getName() + " loaded");
        
        // The following code must be done by the driver
        // TODO: init plus queues
        mydevice.SetReset();
        
        
      /*  --The driver MUST follow this sequence to initialize a device:

        	--Reset the device.
        	--Set the ACKNOWLEDGE status bit: the guest OS has noticed the device.
        	--Set the DRIVER status bit: the guest OS knows how to drive the device.
        	--Read device feature bits, and write the subset of feature bits understood by the OS and driver to the device. During this step the driver MAY read (but MUST NOT write) the device-specific configuration fields to check that it can support the device before accepting it.
        	--Set the FEATURES_OK status bit. The driver MUST NOT accept new feature bits after this step.
        	--Re-read device status to ensure the FEATURES_OK bit is still set: otherwise, the device does not support our subset of features and the device is unusable.
        	--Perform device-specific setup, including discovery of virtqueues for the device, optional per-bus setup, reading and possibly writing the device’s virtio configuration space, and population of virtqueues.
        	--Set the DRIVER_OK status bit. At this point the device is “live”.*/
        
	}
	
}
