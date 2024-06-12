package cli.command;

import app.AppConfig;

public class AddFriendCommand implements CLICommand {

    @Override
    public String commandName() {return "add_friend";}

    @Override
    public void execute(String args) {
        AppConfig.timestampedStandardPrint("Adding friend...");
        if(!args.contains(":") || args.split(":").length != 2){
            AppConfig.timestampedErrorPrint("Add friend command should look like this: add_friend friendIpAdress:friendPort");
            return;
        }

        String[] splitArgs = args.split(":");

        AppConfig.chordState.addFriend(splitArgs[0], Integer.parseInt(splitArgs[1]));
        AppConfig.timestampedStandardPrint("Friend " + args + " added.");
    }
}
