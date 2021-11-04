package dictionary.dictionarypro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslatorUsingAPI {
    public static String translate(String text) throws IOException {

        // Tạo 1 url kiểu String.
        String urlStr = "https://script.google.com/macros/s/AKfycbxsjptjQYjsO7MZ3bOtV7v"
                + "IZoCIesUCqs6KUZgINNU6Ms2djo0C6VfA7PngeA101bje/exec"
                + "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=vi&source=en";

        /**
         *  gửi yêu cầu Java HTTP sử dụng lớp HttpURLConnection
         .*/

        URL url = new URL(urlStr);
        //Mở một kết nối tới URL, cho phép một client giao tiếp với nguồn.
        HttpURLConnection obj = (HttpURLConnection) url.openConnection();

        // set thuộc tính yêu cầu.
        // Mozilla/5.0 là chuỗi User Agent của Mozilla.
        // User Agent là chuỗi nhận dạng trình duyệt.
        obj.setRequestProperty("User-Agent", "Mozilla/5.0");

        //tạo một InputStreamReader để đọc obj.getInputStream().
        // obj.getInputStream() trả về luồng đầu vào đọc từ kết nối mở.
        InputStreamReader str = new InputStreamReader(obj.getInputStream());
        BufferedReader input = new BufferedReader(str);

        // tạo biến response kiểu StringBuilder để lưu từ đã dịch.
        // StringBuilder được sử dụng để tạo chuỗi có thể thay đổi.
        StringBuilder response = new StringBuilder();
        //String response = "";

        String inputLine;
        // sử dụng readLine() để đọc từng dòng.
        while ((inputLine = input.readLine()) != null) {
            response.append(inputLine);
            //response += inputLine;
        }
        input.close();
        return response.toString();
    }
}
