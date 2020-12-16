import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MoviesDirectory {
    public MoviesDirectory(String FolderName){
        this.folderName=FolderName;
    }
    private final String folderName;

    public void moveFileNUpload() {
        File directory = new File(folderName);

        Runnable fileCheck = () -> {
            System.out.println(directory.toPath().toString());
            if(directory.exists()) {
                String[] files = directory.list();
                if(files != null){
                    for (String file:files) {
                        System.out.println("leci plik");
                        try {
                            if(GDriveUpload.DriveUpload(file, folderName)){
                                File movie = new File(folderName + "/"+ file);
                                movie.delete();
                            }
                        } catch (GeneralSecurityException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        ScheduledExecutorService fileCheckTimer = Executors.newSingleThreadScheduledExecutor();
        fileCheckTimer.scheduleWithFixedDelay(fileCheck, 0, 5, TimeUnit.MINUTES);
    }

    public void moveLeftOverNUpload(){
        File directory = new File(folderName);
        if(directory.exists()) {
            String[] files = directory.list();
            if(files != null){
                for (String file:files) {
                    System.out.println("leci plik");
                    try {
                        if(GDriveUpload.DriveUpload(file, folderName)){
                            File movie = new File(folderName + "/"+ file);
                            movie.delete();
                        }
                    } catch (GeneralSecurityException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
