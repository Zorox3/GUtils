package gUtil10;

import gUtil10.config.GU;
import gUtil10.database.Database;
import gUtil10.files.loader.FileLoader;
import gUtil10.files.loader.ImageLoader;
import gUtil10.parser.text.TextParser;
import gUtil10.parser.text.dataManagement.TPData;
import gUtil10.parser.text.dataManagement.TPValue;
import gUtil10.textures.Texture;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class GU10 {

	private static final String VERSION = "GUtils 1.0 (Beta) - 27.06.2016 | 12:53 GMT";
	
	private static boolean debugMode = false;

	private static ImageLoader imageLoader = new ImageLoader();
	private static TextParser textParser;
	private static Database database;

	public static void guVersion(){
		System.out.println(VERSION);
		System.out.println();

	}
	
	public static boolean isDebugging() {
		return debugMode;
	}

	public static void guEnable(GU enable) {
		switch (enable) {
		case GU_DEBGUG:
			debugMode = true;
			break;

		default:
			break;
		}
	}

	public static void guDisable(GU enable) {
		switch (enable) {
		case GU_DEBGUG:
			debugMode = false;
			break;
		default:
			break;
		}
	}

	/**
	 * Creates a Texture from a given image filename
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
	public static void guRegisterTexture(String name, Object... params) {
		Texture.register(name, params);
	}

	/**
	 * Creates a Texture from a given image filename
	 * 
	 * @param name
	 */
	public static void guRegisterTexture(String name) {
		Texture.register(name, new Object[] {});

	}

	/************************Image-Loader**************************/
	/**
	*Scans the given folder and registers all files as Images if possible
	* 
	* @param path
	*/

	public static void guRegisterImageFolder(String path) {
		imageLoader.load(path);
	}

	/************************File-Loader**************************/
	/**
	 * Scans the given folder and registers all files as java.io.File
	 * 
	 * @param path
	 */

	public static void guRegisterFiles(String path) {
		new FileLoader(path);
	}

	/**
	 * returns a file from the internal filelist
	 * 
	 * @param filename
	 * @return File
	 */
	public static File guGetFileByName(String filename) {
		return FileLoader.getFile(filename);
	}

	/*************************Text-Parser***************************/

	/**
	 * parses the given File
	 * variablen: var1 = 1.2
	 * arrays: array1 = { 1, 2, 3, key > 4, 5, 6 }
	 * 
	 * @param file
	 */

	public static void guParseTextFile(String file) {
		textParser = new TextParser(file);
		textParser.process();
	}

	/**
	 * returns the values of a parsed file
	 * 
	 * @param key
	 * @return TPData
	 */
	public static TPData guGetParseData(String key) {
		return textParser.get(key);
	}

	/**
	 * returns all variables as an 2 dimensonal ArrayList
	 * 
	 * @return ArrayList<ArrayList<TPValue>>
	 */
	public static ArrayList<ArrayList<TPValue>> guGetParseDataAsArrayList() {
		return textParser.getDataAsArrayList();
	}

	/**
	 * returns an Raw Variable Map
	 * 
	 * @return Map<String, TPData>
	 */
	public static Map<String, TPData> guGetParseData() {
		return textParser.getData();
	}

	/****************************Database****************************/

	/**
	 * connects to an existing SQLite-File or creates one
	 * 
	 * @param dbName
	 */

	public static void guSQLiteDatabase(String dbName) {
		database = new Database(dbName);
	}

	/**
	 * 
	 * creates a connection to an MySQL-Database
	 * 
	 * @param databasePath
	 * @param databaseName
	 * @param username
	 * @param password
	 */
	public static void guMySQLDatabase(String databasePath, String databaseName, String username, String password) {
		database = new Database(databasePath, databaseName, username, password);
	}

	/**
	 * returns the database connection
	 * 
	 * @return
	 */

	public static Database guDatabase() {
		return database;
	}

}
