import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OnlinerParse {
    static ArrayList<Article> articleList= new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Document connect = Jsoup.connect("https://www.onliner.by/").get();
        Elements h2Elements = connect.getElementsByAttributeValue("class","b-main-page-grid-4__col b-main-page-grid-4__col-1x2 news-2__main-news-col");
        h2Elements.forEach(h2Element -> {
            Element aElement = h2Element.child(0).child(1).child(0).child(1).child(0);
          String url=aElement.attr("href");

          String title=aElement.text();
           articleList.add(new Article(url,title));
            });
        window.show(articleList);
        articleList.forEach(System.out::println);
    }


    public static class window{
        public static void show(ArrayList<Article> articleList) {
            String string= new String();
            String ustring= new String();
            JFrame frame= new JFrame("onliner");
            JPanel panel= new JPanel();

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(1400,400);

            for(int i = 0; i< articleList.size(); i++)
            {
                string+=OnlinerParse.articleList.get(i).getTitle();
                ustring+=OnlinerParse.articleList.get(i).getUrl();
                string+=("%");
                ustring+=(",");
            }

            String[] textSplit = ustring.split(",");
            String[] urlSplit= string.split("%");
            System.out.println(string);
            System.out.println(ustring);

            for(int i=0;i<textSplit.length;i++)
            {
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                JTextArea textArea= new JTextArea(textSplit[i]);
                JTextArea urlArea= new JTextArea(urlSplit[i]);
                panel.add(new JScrollPane(urlArea));
                panel.add(new JScrollPane(textArea));
            }

            frame.add(panel);
            frame.setVisible(true);
        }
    }
}
class Article{
    private String url;
    private String title;
    public Article(String url, String title)
    {
        this.title=title;
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
