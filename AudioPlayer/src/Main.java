import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static ArrayList<String> fullpath = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {




        String folderPath = "//home//ab//Downloads//songs//";

        Map<String,String> songs = new HashMap<String,String>();




        try {
            Files.list(Paths.get(folderPath)).filter(Files::isRegularFile).forEach(path -> fullpath.add(path.toString()));
        }catch (IOException e){
            System.out.println(e);
        }



        for (String path : fullpath){
            String songName = path.substring(25);
            songs.put(songName,path);

        }


            String filePath = "//home//ab//Downloads//songs//Eminem-Mockingbird.wav";

            String filepathFull = "//home//ab//Downloads//songs//" ;

            File file = new File(filePath);

            try (AudioInputStream audio = AudioSystem.getAudioInputStream(file)){

                Clip clip = AudioSystem.getClip();
                clip.open(audio);

                while (true) {


                    System.out.print("what do you wanna do ?\n1-play\n2-resume\n3-stop\n4-Change\n5-Quit \nEnter your Choice: ");
                    String userChoice = scanner.next();
                    System.out.println("");
                    scanner.nextLine();



                    switch (userChoice){
                        case "1"->{
                            clip.start();
                        }



                    case "2"->{
                        clip.setMicrosecondPosition(0);
                    }

                    case "3"->{
                        clip.stop();
                    }

                    case "4"->{
                        clip.stop();
                            String song = userI();
                        try {

                            clip.close();
                            int realSongNum = Integer.parseInt(song) -1;
                            String songPath = fullpath.get(realSongNum);
                            songPath = filepathFull + songPath.substring(25);
                            //System.out.println(songPath);


                            file = new File(songPath);

                            //System.out.println(openSong);
                            AudioInputStream audio2 = AudioSystem.getAudioInputStream(file);
                            clip.open(audio2);
                            clip.start();


                        }catch (Exception e){
                            System.out.println("invailed Choice");
                            continue;
                        }


                    }
                    case "5"->{
                        return;
                    }
                        default->{
                            System.out.println("invailed Choice");

                        }
                    }


                }
            }catch (IOException e){
                System.out.println("something went wrong");
            }catch (UnsupportedAudioFileException e){
                System.out.println("unsupported Audio ");
            }catch (LineUnavailableException e){
                System.out.println("LineUnavailableException");
            }finally {
                System.out.println("Bye");
                scanner.close();
            }




    }

    static String userI (){
        int songNum = 1;
        System.out.println("songs List :");

        for (String songN : fullpath){
            System.out.println(songNum+": "+songN.substring(25));
            songNum+=1;
        }

        System.out.print("Enter your chioce: ");
        String song = scanner.nextLine();
        return song;
    }

}