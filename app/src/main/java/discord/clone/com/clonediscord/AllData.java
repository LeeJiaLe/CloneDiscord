package discord.clone.com.clonediscord;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class AllData {
     static int channelNum=0;
     static int messageNum=0;
     static Calendar c = Calendar.getInstance();
     static Date initMsgDate = new Date();

    static User[] users = {
            new User("Sample User 1","#ff3300",R.drawable.cat_0),
            new User("Sample User 2","#0099ff",R.drawable.cat_1),
            new User("Sample User 3","#cc0099",R.drawable.cat_2),
            new User("Sample User 4","#ff6600",R.drawable.cat_3),
            new User("Sample User 5","#ff0066",R.drawable.cat_4),
            new User("Sample User 6","#00ff00",R.drawable.cat_5),
            new User("Sample User 7","#ff9900",R.drawable.cat_6),
            new User("Sample User 8","#cc0066",R.drawable.cat_7),
            new User("Sample User 9","#0066ff",R.drawable.cat_8),
            new User("Sample User 10","#ffff66",R.drawable.cat_9),
            new User("Sample User 11","#ff3300",R.drawable.cat_0),
            new User("Sample User 12","#0099ff",R.drawable.cat_1),
            new User("Sample User 13","#cc0099",R.drawable.cat_2),
            new User("Sample User 14","#ff6600",R.drawable.cat_3),
            new User("Sample User 15","#ff0066",R.drawable.cat_4),
            new User("Sample User 16","#00ff00",R.drawable.cat_5),
            new User("Sample User 17","#ff9900",R.drawable.cat_6),
            new User("Sample User 18","#cc0066",R.drawable.cat_7),
            new User("Sample User 19","#0066ff",R.drawable.cat_8),
            new User("Sample User 20","#ffff66",R.drawable.cat_9),
    };
    static ServerData[] serverData;
    AllData(){
        c.set(2019,4,1,0,0);
        initMsgDate.setTime(c.getTimeInMillis());

        serverData= new ServerData[]{
                new ServerData(false, "server1", R.drawable.cat_8, 1, 0, new ChannelGroup[]{
                        new ChannelGroup("Group1", randChannel(5)),
                        new ChannelGroup("Group2", randChannel(5)),
                        new ChannelGroup("Group3", randChannel(5)),
                        new ChannelGroup("Group4", randChannel(5)),
                        new ChannelGroup("Group5", randChannel(5)),
                        new ChannelGroup("Group1", randChannel(5)),
                        new ChannelGroup("Group2", randChannel(5)),
                        new ChannelGroup("Group3", randChannel(5)),
                        new ChannelGroup("Group4", randChannel(6)),
                        new ChannelGroup("Group5", randChannel(5)),
                        new ChannelGroup("Group1", randChannel(5)),
                        new ChannelGroup("Group2", randChannel(7)),
                        new ChannelGroup("Group3", randChannel(5)),
                        new ChannelGroup("Group4", randChannel(6)),
                        new ChannelGroup("Group5", randChannel(4)),
                        new ChannelGroup("Group1", randChannel(4)),
                        new ChannelGroup("Group2", randChannel(7)),
                        new ChannelGroup("Group3", randChannel(5)),
                        new ChannelGroup("Group4", randChannel(3)),
                        new ChannelGroup("Group5", randChannel(4)),
                }),
                new ServerData(false, "server2", R.drawable.cat_1, 3, 2, new ChannelGroup[]{
                        new ChannelGroup("Group7", randChannel(6)),
                        new ChannelGroup("Group8", randChannel(2)),
                        new ChannelGroup("Group3", randChannel(5)),
                        new ChannelGroup("Group4", randChannel(5)),
                        new ChannelGroup("Group5", randChannel(2)),
                        new ChannelGroup("Group7", randChannel(5)),
                        new ChannelGroup("Group8", randChannel(2)),
                        new ChannelGroup("Group3", randChannel(5)),
                        new ChannelGroup("Group4", randChannel(3)),
                        new ChannelGroup("Group5", randChannel(3)),
                        new ChannelGroup("Group7", randChannel(4)),
                        new ChannelGroup("Group8", randChannel(2)),
                        new ChannelGroup("Group3", randChannel(5)),
                        new ChannelGroup("Group4", randChannel(5)),
                        new ChannelGroup("Group5", randChannel(12)),
                        new ChannelGroup("Group7", randChannel(5)),
                        new ChannelGroup("Group8", randChannel(2)),
                        new ChannelGroup("Group3", randChannel(2)),
                        new ChannelGroup("Group4", randChannel(3)),
                        new ChannelGroup("Group5", randChannel(6)),
                        new ChannelGroup("Group7", randChannel(5)),
                        new ChannelGroup("Group8", randChannel(4)),
                        new ChannelGroup("Group3", randChannel(3)),
                        new ChannelGroup("Group4", randChannel(3)),
                        new ChannelGroup("Group5", randChannel(2)),
                }),
                new ServerData(true, "server3", R.drawable.cat_0, 4, 0, new ChannelGroup[]{
                        new ChannelGroup("Group7", randChannel(6)),
                        new ChannelGroup("Group8", randChannel(2)),
                        new ChannelGroup("Group3", randChannel(5)),
                        new ChannelGroup("Group4", randChannel(5)),
                        new ChannelGroup("Group5", randChannel(2)),
                        new ChannelGroup("Group7", randChannel(5)),
                        new ChannelGroup("Group8", randChannel(2)),
                        new ChannelGroup("Group3", randChannel(5)),
                        new ChannelGroup("Group4", randChannel(3)),
                        new ChannelGroup("Group5", randChannel(3)),
                        new ChannelGroup("Group7", randChannel(4)),
                        new ChannelGroup("Group8", randChannel(2)),
                        new ChannelGroup("Group3", randChannel(5)),
                        new ChannelGroup("Group4", randChannel(5)),
                        new ChannelGroup("Group5", randChannel(12)),
                        new ChannelGroup("Group7", randChannel(5)),
                        new ChannelGroup("Group8", randChannel(2)),
                        new ChannelGroup("Group3", randChannel(2)),
                        new ChannelGroup("Group4", randChannel(3)),
                        new ChannelGroup("Group5", randChannel(6)),
                        new ChannelGroup("Group7", randChannel(5)),
                        new ChannelGroup("Group8", randChannel(4)),
                        new ChannelGroup("Group3", randChannel(3)),
                        new ChannelGroup("Group4", randChannel(3)),
                        new ChannelGroup("Group5", randChannel(2)),
                }),
        };
    }





    public static Channel[] randChannel(int i){
        Channel[] ch = new Channel[i];
        int index=0;
        while(i>0){
            i--;
            ch[index]=new Channel("channel"+String.valueOf(AllData.channelNum),"text");
            AllData.channelNum++;
            index++;
        }

        return ch;
    }
}

class ServerData{
    private ChannelGroup[] channelGroups;
    private Boolean selected;
    private String serverName;
    private int imgResource;
    int selectedCGIndex;
    int selectedCIndex;
    ServerData(Boolean selected, String serverName,int re,int g,int c, ChannelGroup[] ch){
        this.selected = selected;
        this.channelGroups = ch;
        this.serverName = serverName;
        this.imgResource = re;
        this.selectedCIndex = c;
        this.selectedCGIndex = g;
    }

    public String getServerName() {
        return serverName;
    }

    int getImgResource() {
        return imgResource;
    }

    Boolean getSelected() {
        return selected;
    }

    void setSelected(Boolean selected) {
        this.selected = selected;
    }

    void setChannelGroups(ChannelGroup[] channelGroups){
        this.channelGroups = channelGroups;
    }

    ChannelGroup[] getChannelGroup(){return this.channelGroups;}

    void setChannelSelected(int pos,boolean b){
        this.channelGroups[pos].setSelected(b);
    }

    boolean getChannelSelected(int pos){
        return this.channelGroups[pos].getSelected();
    }
}

class ChannelGroup{
    private Channel[] channels;
    private String groupName;
    private Boolean selected;
    ChannelGroup(String groupName, Channel[] channels){
        this.groupName = groupName;
        this.selected = false;
        this.channels = channels;
    }

    void setSelected(Boolean selected) {
        this.selected = selected;
    }

    Boolean getSelected() {
        return selected;
    }

    void setChannels(Channel[] channels) {
        this.channels = channels;
    }

    String getGroupName() {
        return groupName;
    }

    Channel[] getChannels() {
        return channels;
    }
}

class Channel{
    private String name;
    private String type;
    private DiscordMessage[] discordMessages;
    Channel(String name, String type){
        this.name=name;
        this.type=type;
        this.discordMessages = new DiscordMessage[10];

        for(int i=0;i<this.discordMessages.length;i++){
            int rnd = new Random().nextInt(4)+1;
            String[] message = new String[rnd];

            for(int j=0;j<message.length;j++){
                message[j]="Meow "+String.valueOf(AllData.messageNum);

                AllData.messageNum++;
            }
            Date date = new Date();
            date.setTime(AllData.c.getTimeInMillis()+300000);
            this.discordMessages[i] = new DiscordMessage(message,date);

            AllData.c.setTime(date);
        }
    }

    public void setDiscordMessages(DiscordMessage[] discordMessages) {
        this.discordMessages = discordMessages;
    }

    public DiscordMessage[] getDiscordMessages() {
        return discordMessages;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}

class DiscordMessage{
    private String[] message;
    private Date date;
    private User user;
    DiscordMessage(String[] message, Date date){
        int rnd = new Random().nextInt(AllData.users.length);
        this.user = AllData.users[rnd];
        this.date=date;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public String[] getMessage() {
        return message;
    }
}

class User{
    private String textColor;
    private String userName;
    private int profilePic;

    User(String userName, String textColor, int profilePic){
        this.textColor = textColor;
        this.userName = userName;
        this.profilePic = profilePic;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getUserName() {
        return userName;
    }

    public int getProfilePic() {
        return profilePic;
    }
}