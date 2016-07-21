// by Brian Wong and Laszlo Glant

package SongLibView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.collections.ObservableList;

public class Song {
	public String songName;
	public String artist;
	public String album;
	public String year;

	public static int addIndex;

	public Song(String songName, String artist, String album, String year) {
		this.songName = songName;
		this.artist = artist;
		this.album = album;
		this.year = year;		
	}

	public String toString() {
		return songName + ", " + artist + ", " + album + ", " + year;
	}

	public String outString() {
		return songName + "~" + artist + "~" + album + "~" + year;
	}

	static ArrayList<Song> songList = new ArrayList<Song>();

	/**
	 * sort all entries in obsList in alphabetical order, not used
	 * @param obsList observable list containing songs
	 */
	public static void sortAbc(ObservableList<Song> obsList) {
		Collections.sort(obsList, new Comparator<Song>() {
			@Override
			public int compare(Song s1, Song s2) {
				return s1.songName.compareToIgnoreCase(s2.songName);
			}
		});
	}

	/**
	 * sort all Strings in obsList in alphabetical order
	 * @param obsList observable list containing Strings
	 */
	public static void sortAbcStrings(ObservableList<String> obsList) {
		Collections.sort(obsList, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareToIgnoreCase(s2);
			}
		});
	}

	/**
	 * sort all entries in songList in alphabetical order
	 * @param songList array list containing songs
	 */
	public static void sortAbcAL(ArrayList<Song> songList) {
		Collections.sort(songList, new Comparator<Song>() {
			@Override
			public int compare(Song s1, Song s2) {
				return s1.songName.compareToIgnoreCase(s2.songName);
			}
		});
	}
	/**
	 * delete selected song from songList
	 * 
	 * @param songList list of songs, should always be in abc order
	 * @param obsList list of song titles, should always be in abc order
	 * @param index song will be deleted from songList
	 * @throws IOException 
	 */
	public static boolean deleteSong(ArrayList<Song> songList, ObservableList<String> obsList, int index) throws IOException {
		if(songList.isEmpty()){
			return false;
		}
		else{
			songList.remove(index);
			SongViewController.obsList.remove(index);
			File file = new File("output.txt");
			PrintWriter out = new PrintWriter(new FileWriter(file, false), true);
			for (int i = 0; i < songList.size(); i++) {
				out.println(songList.get(i).outString());
			}
			out.close();
			if (index == songList.size()){
				SongViewController.index=index-1;
			}
			return true;
		}
	}


	/**
	 * add newSong in correct spot in songList
	 * @param songList list of songs, should always be in abc order
	 * @param obsList list of song titles, should always be in abc order
	 * @param newSong Song instance that is about to be added into songList
	 */
	public static boolean addInAbcOrder(ArrayList<Song> songList, ObservableList<String> obsList, Song newSong) {
		String con = newSong.songName+"***"+newSong.artist;
		if (songList.isEmpty()) {
			// no songs, add songName here
			songList.add(newSong);
			obsList.add(newSong.songName);
			SongViewController.index = 0;
			return true;
		}
		for (int i=0; i < songList.size(); i++) {
			//in the list already
			if(con.compareToIgnoreCase(songList.get(i).songName+"***"+songList.get(i).artist) == 0){
				return false;
	        }
			//not in the list add it at index
			if (con.compareToIgnoreCase(songList.get(i).songName+"***"+songList.get(i).artist) < 0) {
				songList.add(i, newSong);
				obsList.add(i, newSong.songName);
				SongViewController.index = i;
				return true;
	        }
			
		}
		//add as last item
		songList.add(newSong);
		obsList.add(newSong.songName);
		SongViewController.index = songList.size()-1;
		return true;
	}
	/**
	 * goes through every song in songList and stores in output.txt
	 * @param songList list of songs, should be in abc order
	 * @return true if success, false otherwise
	 */
	public static boolean output(ArrayList<Song> songList) {
		try {
			File o = new File("output.txt");
			o.createNewFile();

			PrintWriter out = new PrintWriter(new FileWriter(o, false), true);
			for (int i = 0; i < songList.size(); i++) {
				out.println(songList.get(i).outString());
			}
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("Error reading file in Control.output");
			return false;
		} catch (Exception e) {
			System.out.println("Exception in Control.output");
			return false;
		}
	}

	/**
	 * takes in emptyList and file (output.txt) and puts lines in file into emptyList
	 * @param emptyList list of songs, should be empty when this called
	 * @param obsList list of song titles, should also be empty when this called
	 * @param o output.txt
	 * @return true if success, false otherwise
	 */
	public static boolean input(ArrayList<Song> emptyList, ObservableList<String> obsList, File o) {
		String line = null;

		try {
			FileReader fileReader = new FileReader(o);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				stickIn1Line(emptyList, line);
				addObs1Entry(obsList, line);
			}

			bufferedReader.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return false;
		} catch (IOException e) {
			System.out.println("Error reading file");
			return false;
		} catch (Exception e) {
			System.out.println("Exception in Song.input");
			return false;
		}
	}

	public static boolean inputObs(ObservableList<String> obsList, File o) {
		String line = null;

		try {
			FileReader fileReader = new FileReader(o);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				addObs1Entry(obsList, line);
			}
			bufferedReader.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return false;
		} catch (IOException e) {
			System.out.println("Error reading file");
			return false;
		} catch (Exception e) {
			System.out.println("Exception in Song.inputObs");
			return false;
		}
	}

	/**
	 * create new Song instance with line (songName~artist~album~year) and add to emptyList
	 * @param emptyList list of songs, should be empty first time this method called
	 * @param line (songName~artist~album~year)
	 */
	public static void stickIn1Line(ArrayList<Song> emptyList, String line) {
		String[] strArr = line.split("~");
		emptyList.add(new Song(strArr[0], strArr[1], strArr[2], strArr[3]));
	}

	/**
	 * add title of song to obsList
	 * @param obsList list of song titles
	 * @param line (songName~artist~album~year)
	 */
	public static void addObs1Entry(ObservableList<String> obsList, String line) {
		String[] strArr = line.split("~");

		obsList.add(strArr[0]);
	}

	/**
	 * prints out each song in songList, only used for testing purposes
	 * @param songList list of songs, should always be in abc order
	 */
	public static void printList(ArrayList<Song> songList) {
		for (int i = 0; i < songList.size(); i++) {
			System.out.println(i + " " + songList.get(i).toString());
		}
	}

	public static void printObsList(ObservableList<Song> obsList) {
		for (int i = 0; i < obsList.size(); i++) {
			System.out.println(i + " " + obsList.get(i).toString());
		}
	}

	public static void printBothLists(ArrayList<Song> songList, ObservableList<String> obsList) {
		//System.out.println("al size " + songList.size());
		//System.out.println("ol size " + obsList.size());

		if (obsList.size() == 0) {
			System.out.println("obslist is empty");
			return;
		}
		for (int i = 0; i < obsList.size(); i++) {
			System.out.println(i + " " + songList.get(i).toString() + " " + obsList.get(i));
		}
	}
}
