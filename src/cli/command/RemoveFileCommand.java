package cli.command;

import app.AppConfig;
import app.MyFile;
import app.TokenMutex;

public class RemoveFileCommand implements CLICommand {

    @Override
    public String commandName() {
        return "remove_file";
    }

    @Override
    public void execute(String args) {
        AppConfig.timestampedStandardPrint("Removing file...");
        if(args.isEmpty()){
            AppConfig.timestampedErrorPrint("Remove file command should look like this: remove_file path");
            return;
        }

        int index = -1;
        for(int i = 0; i < AppConfig.chordState.getFiles().size(); i++){
            AppConfig.timestampedStandardPrint("File path: " + AppConfig.chordState.getFiles().get(i).getPath() + " args: " + args);
            if(AppConfig.chordState.getFiles().get(i).getPath().equals(args)){
                index = i;
                break;
            }
        }

        if(index == -1){
            AppConfig.timestampedErrorPrint("File not found");
            return;
        }

        MyFile fileToRemove = AppConfig.chordState.getFiles().get(index);
        if (fileToRemove.getOwner().equals(AppConfig.myServentInfo)) {
            TokenMutex.lock();
            AppConfig.chordState.removeFile(fileToRemove);
            TokenMutex.unlock();
        } else
            AppConfig.timestampedErrorPrint("This node is not the main holder of the file, can not delete selected file");

//        if(fileToRemove != null){
//            if(fileToRemove.getOwner().equals(AppConfig.myServentInfo)){
//                AppConfig.chordState.removeFile(fileToRemove, AppConfig.myServentInfo.getIpAddress(), AppConfig.myServentInfo.getListenerPort());
//            } else {
//                AppConfig.timestampedErrorPrint("This node is not the main holder of the file, can not delete selected file");
//            }
//        }
    }
}
