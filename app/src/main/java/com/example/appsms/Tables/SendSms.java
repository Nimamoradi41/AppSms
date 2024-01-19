package com.example.appsms.Tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "SendSms")
public class SendSms {
    @PrimaryKey(autoGenerate = true)
    public Integer iddatabase;

    // @ColumnInfo(name="idNumberRecive")
    // public Integer idNumberRecive;

    @ColumnInfo(name = "NameReciveNumber")
    public String NameReciveNumber;

    @ColumnInfo(name = "idNumberRecive")
    public List<Integer> idNumberRecive;

    @ColumnInfo(name = "Number")
    public String Number;

    @ColumnInfo(name = "Name")
    public String Name;

    public Integer getIddatabase() {
        return iddatabase;
    }

    public void setIddatabase(Integer iddatabase) {
        this.iddatabase = iddatabase;
    }

    public String getNameReciveNumber() {
        return NameReciveNumber;
    }

    public void setNameReciveNumber(String nameReciveNumber) {
        NameReciveNumber = nameReciveNumber;
    }

    public List<Integer> getIdNumberRecive() {
        return idNumberRecive;
    }

    public void setIdNumberRecive(List<Integer> idNumberRecive) {
        this.idNumberRecive = idNumberRecive;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}