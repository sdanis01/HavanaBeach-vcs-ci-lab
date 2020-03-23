package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private Thread thread;
	private boolean stop = false;

	/*
	Setting the reference speed of the train works in the following way.
			* The user can set the position of a joystick, which can be zero or a positive/negative number.
			* In each time unit, the controller adds the position of the joystick to the current reference speed. Therefore the reference speed does not change if the position is zero, it is incremented if the position is positive and it is decremented if the position is negative.
			* After each change, the speed limit is checked and enforced.
	*/
	public TrainControllerIMpl(){
		Thread t = new Thread(){
			public void run() {
				while (!stop) {
					try {
						followSpeed();
						thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}

	public void stop(){stop = true;}
	public void start(){stop = false;}

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

}
