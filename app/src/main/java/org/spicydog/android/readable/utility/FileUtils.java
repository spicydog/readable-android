package org.spicydog.android.readable.utility;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by spicydog on 2015-03-23.
 */
public class FileUtils {

    public static void saveStringFromFile(Context context, String filename, String data) {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String loadStringToFile(Context context, String filename, String defaultString) {

        FileInputStream fis;
        String data = defaultString;

        try {
            fis = context.openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            while ((data = bufferedReader.readLine()) != null) {
                sb.append(data);
            }
            data = sb.toString();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
