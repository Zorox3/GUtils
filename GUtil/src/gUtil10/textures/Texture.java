package gUtil10.textures;

import gUtil10.files.loader.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Texture {

	public static Map<String, Texture> textureList = new HashMap<>();

	private String name;
	private boolean isSprite;
	private int rows;
	private int cols;
	private int width;
	private int height;
	private int offsetX = 0;
	private int offsetY = 0;

	private Texture(String name, Object... params) {
		this.name = name;
		if (params.length > 0) {
			this.isSprite = (boolean) params[0];
			this.rows = (int) params[1];
			this.cols = (int) params[2];
			this.width = (int) params[3];
			this.height = (int) params[4];
			if (params.length > 5) {
				this.offsetX = (int) params[5];
				this.offsetY = (int) params[6];
			}
		}
	}

	public BufferedImage getTexture() {
		return ImageLoader.getImage(name);
	}

	public String getName() {
		return name;
	}

	public boolean isSprite() {
		return isSprite;
	}

	public int getCols() {
		return cols;
	}

	public int getHeight() {
		return height;
	}

	public int getRows() {
		return rows;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public int getWidth() {
		return width;
	}

	public static Texture get(String text) {
		return textureList.get(text);
	}

	/**
	 * 
	 * @param name
	 * @param params
	 * 		-	boolean isSprite;
	 		-	int rows;
	 		-	int cols;
	 		-	int width;
	 		-	int height;
	 		-	int offsetX = 0;
	 		-	int offsetY = 0;
	 */
	public static void register(String name, Object... params) {
		textureList.put(name, new Texture(name, params));
	}

	
	
}
