package renderEngine;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class DisplayManager {
	public static long window;
	private static GLFWErrorCallback errorCallback;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	public static void createDisplay() {
		errorCallback = GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		window = glfwCreateWindow(WIDTH, HEIGHT, "Galactic Pandas", NULL, NULL);
		if (window == NULL) {
			glfwTerminate();
			throw new RuntimeException("Failed to create the GLFW window");
		}
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
				glfwSetWindowShouldClose(window, true);
			}
		});
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
	}
	
	public static void updateDisplay() {
		while (!glfwWindowShouldClose(window)) {
			double time = glfwGetTime();
			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}
	
	public static void closeDisplay() {
		glfwDestroyWindow(window);
		Callbacks.glfwFreeCallbacks(window);
		glfwTerminate();
		errorCallback.free();
	}
}
