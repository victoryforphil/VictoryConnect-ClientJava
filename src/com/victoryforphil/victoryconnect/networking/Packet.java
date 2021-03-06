package com.victoryforphil.victoryconnect.networking;

import com.victoryforphil.victoryconnect.util.Consts;

public class Packet {
    public enum DataType {ERROR, SUBMIT, REQUEST, COMMAND}
    public DataType type;
    public String path;
    public String[] data;
    public String protocol = "DEFAULT";
    public String raw;

    public Packet(DataType type, String path, Object[] dataObj){
        this.type = type;
        this.path = path;
        data = new String[dataObj.length];
        for(int i=0;i<dataObj.length;i++){
            data[i] = dataObj[i].toString();
        }

        //System.out.println("Converted Array: " + this.toString());
    }

    public Packet(DataType type, String path, Object dataObj){
        this.type = type;
        this.path = path;
        data = new String[1];
        data[0] = dataObj.toString();

        //System.out.println("Converted: " + this.toString());
    }

  

    public Packet setProtocol(String protocol){
        this.protocol = protocol;
        return this;
    }

    public Packet setRaw(String raw){
        this.raw = raw;
        return this;
    }


    @Override
    public String toString() {
        return getTypeInt() + " " + path + " {" + String.join(";", data) + "}~";
    }

    public int getTypeInt(){
        int typeInt = 0;
        switch (type) {
            case ERROR:
                typeInt = Consts.PacketType.ERROR;
                break;
            case SUBMIT:
                typeInt = Consts.PacketType.SUBMIT;
                break;
            case REQUEST:
                typeInt = Consts.PacketType.REQUEST;
                break;
            case COMMAND:
                typeInt = Consts.PacketType.COMMAND;
                break;
        }
        return typeInt;
    }

    public static DataType typeFromInt(int inout){
        switch (inout){
            case Consts.PacketType.ERROR:
                return DataType.ERROR;


            case Consts.PacketType.SUBMIT:
                return DataType.SUBMIT;


            case Consts.PacketType.REQUEST:
                return DataType.REQUEST;


            case Consts.PacketType.COMMAND:
                return DataType.COMMAND;


            default:
                return DataType.ERROR;
        }
    }
}
