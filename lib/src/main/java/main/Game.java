package main;

import renderEngine.DisplayManager;

public class Game {
	public void run() {
		init();
		loop();
		destroy();
	}
	
	private void init() {
		DisplayManager.createDisplay();
	}
	
	private void loop() {
		DisplayManager.updateDisplay();
	}
	
	private void destroy() {
		DisplayManager.closeDisplay();
	}
	
	public static void main(String[] args) {
		new Game().run();
	}
}
