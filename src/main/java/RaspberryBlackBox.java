import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RaspberryBlackBox {
    public static void main(String... args) throws Exception {

        if(new java.io.File("/home/pi/NodeServer/save").exists()){
            List<String> load = null;
            boolean loaded;
            do {
                loaded = true;
                try {
                    load = Files.readAllLines(Paths.get("/home/pi/NodeServer/save"));
                } catch (IOException e) {
                    e.printStackTrace();
                    loaded = false;
                }
                if(load != null){
                    for (String stream : load){
                        String[] split =  stream.split(">");
                        MoviesDirectory camera = new MoviesDirectory(split[1]);
                        camera.moveFile();
                    }
                }
            }while(!loaded);
        }
    }
}
