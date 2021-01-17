package Jeu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSV {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("carteCSV.csv");
        System.out.println("chemin : "+path.toRealPath(LinkOption.NOFOLLOW_LINKS));

        List<String> lignes = Files.readAllLines(path);
        for(String i : lignes){
            System.out.println(i);
        }
    }

}
