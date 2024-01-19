package com.example.appsms;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<Integer> fromString(String value) {
        // اینجا از یک متد خود شما برای تبدیل String به List<Integer> استفاده کنید
        // برای مثال، اگر اعداد با کاما جدا شده باشند، می‌توانید از روش split و Integer.parseInt استفاده کنید.
        List<Integer> myList = new ArrayList<>();
        String[] values = value.split(",");
        for (String stringValue : values) {
            if (!stringValue.isEmpty())
            {
                myList.add(Integer.parseInt(stringValue.trim()));
            }

        }
        return myList;
    }

    @TypeConverter
    public static String fromList(List<Integer> list) {
        // اینجا از یک متد خود شما برای تبدیل List<Integer> به String استفاده کنید
        // برای مثال، می‌توانید از StringBuilder یا join کاراکترها استفاده کنید.
        StringBuilder sb = new StringBuilder();
        for (Integer intValue : list) {
            sb.append(intValue).append(",");
        }
        // حذف کاما اضافی در انتها
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
