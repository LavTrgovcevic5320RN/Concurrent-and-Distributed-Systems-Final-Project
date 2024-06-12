package cli.command;

import app.AppConfig;
import app.MyFile;
import app.ServentInfo;
import app.TokenMutex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ViewFilesCommand implements CLICommand {

    @Override
    public String commandName() {return "view_files";}

    @Override
    public void execute(String args) {
        AppConfig.timestampedStandardPrint("Viewing files...");
        if(!args.contains(":") || args.split(":").length != 2){
            AppConfig.timestampedErrorPrint("View files command should look like this: view_files ipAdress:port");
            return;
        }

        TokenMutex.lock();

        String[] splitArgs = args.split(":");
        ServentInfo serventInfo = new ServentInfo(splitArgs[0], Integer.parseInt(splitArgs[1]));

        boolean isFriend = AppConfig.chordState.isFriend(Integer.parseInt(splitArgs[1]));

        for(MyFile file : AppConfig.chordState.getFiles()){
            if(serventInfo.equals(file.getOwner())){
                if(isFriend){
                    readFile(file);
                } else if(file.getVisibility().equals("public")){
                    readFile(file);
                }
            }
        }

        TokenMutex.unlock();
    }

    private void readFile(MyFile file) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            Files.lines(Paths.get(file.getPath())).forEach(contentBuilder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AppConfig.timestampedStandardPrint("\nContents Of File: \n" + contentBuilder + "\n");
    }
}
