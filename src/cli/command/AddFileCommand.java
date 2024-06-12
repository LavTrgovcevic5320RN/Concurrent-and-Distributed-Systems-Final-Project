package cli.command;

import app.AppConfig;
import app.MyFile;
import app.TokenMutex;

public class AddFileCommand implements CLICommand {

    @Override
    public String commandName() {return "add_file";}

    @Override
    public void execute(String args) {
        AppConfig.timestampedStandardPrint("Adding file...");
        if(!args.contains(" ") || args.split(" ").length != 2){
            AppConfig.timestampedErrorPrint("Add file command should look like this: add_file path private/public");
            return;
        }
        TokenMutex.lock();

        String[] splitArgs = args.split(" ");
        MyFile file = new MyFile(splitArgs[0], splitArgs[1], AppConfig.myServentInfo);
        AppConfig.chordState.addFile(file, AppConfig.myServentInfo.getIpAddress(), AppConfig.myServentInfo.getListenerPort());

        TokenMutex.unlock();
    }
}
