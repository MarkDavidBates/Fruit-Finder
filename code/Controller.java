//TODO: find why ImageArray wont convert elements, get union to work vertically

package Main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    public ImageView imageViewer1, imageViewer2, imageViewer3;
    @FXML
    public ListView imageDetails;
    @FXML
    public Slider sizeSlider;
    public Window stage;
    public int imageArray[];
    public ArrayList<Integer> pixelArray = new ArrayList<Integer>();
    public ArrayList<Integer> clusterArray;
    public Label clusterLabel, pixelLabel;
    public int hashSize;

    FileChooser fileChooser = new FileChooser();

    /*
    opens the OS file browser and adds the jpeg/png file to the pane
    file name, dimensions and pixel details are shown
     */
    public void openFileBrowser(ActionEvent actionEvent) {
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image img = new Image(file.toURI().toString());
            System.out.println("adding image to window...");
            imageViewer1.setImage(img);

            imageDetails.getItems().addAll("File Name: " + file.getName(), "Width: " + img.getWidth() + "pxls", "Height: " + img.getHeight() + "pxls", "<==========>");
        }
    }

    //////////////////////////////
    //Black and White Conversion//
    //////////////////////////////

    public Image convertToBlackAndWhiteBlueberry(Image img) {
        WritableImage wimg = new WritableImage(img.getPixelReader(), (int) img.getWidth(), (int) img.getHeight());
        PixelWriter pxlWriter = wimg.getPixelWriter();
        PixelReader pxlReader = img.getPixelReader();
        imageArray = new int[(int) (wimg.getWidth() * wimg.getHeight())];

        for (int i = 0; i < wimg.getHeight(); i++) {
            for (int j = 0; j < wimg.getWidth(); j++) {
                Color clr = pxlReader.getColor(j, i);
                if (clr.getHue() >= 210 && clr.getHue() <= 260) {
                    pxlWriter.setColor(j, i, Color.WHITE);
                    imageArray[(int) (wimg.getWidth() * i + j)] = (int) (wimg.getWidth() * i + j);
                } else {
                    pxlWriter.setColor(j, i, Color.BLACK);
                    imageArray[(int) (wimg.getWidth() * i + j)] = -1;
                }
            }
        }
        for (int i = 0; i < wimg.getHeight(); i++) {
            for (int j = 0; j < wimg.getWidth(); j++) {
                System.out.print(imageArray[(int) (wimg.getWidth() * i + j)]);
            }
            System.out.println("");
        }
        UnionFind(img);

        System.out.println("<=====================================(displaying array)=====================================>");
        displayArray(img);
        return wimg;
    }

    public Image convertToBlackAndWhiteStrawberry(Image img) {
        WritableImage wimg = new WritableImage(img.getPixelReader(), (int) img.getWidth(), (int) img.getHeight());
        PixelWriter pxlWriter = wimg.getPixelWriter();
        PixelReader pxlReader = img.getPixelReader();
        imageArray = new int[(int) (wimg.getWidth() * wimg.getHeight())];

        for (int i = 0; i < wimg.getHeight(); i++) {
            for (int j = 0; j < wimg.getWidth(); j++) {
                Color clr = pxlReader.getColor(j, i);
                if (clr.getHue() >= 340 && clr.getHue() <= 360) {
                    pxlWriter.setColor(j, i, Color.WHITE);
                    imageArray[(int) (wimg.getWidth() * i + j)] = (int) (wimg.getWidth() * i + j);
                } else {
                    pxlWriter.setColor(j, i, Color.BLACK);
                    imageArray[(int) (wimg.getWidth() * i + j)] = -1;
                }
            }
        }
        for (int i = 0; i < wimg.getHeight(); i++) {
            for (int j = 0; j < wimg.getWidth(); j++) {
                System.out.print(imageArray[(int) (wimg.getWidth() * i + j)]);
            }
            System.out.println("");
        }
        UnionFind(img);

        return wimg;
    }

    public Image convertToBlackAndWhiteOrange(Image img) {
        WritableImage wimg = new WritableImage(img.getPixelReader(), (int) img.getWidth(), (int) img.getHeight());
        PixelWriter pxlWriter = wimg.getPixelWriter();
        PixelReader pxlReader = img.getPixelReader();
        imageArray = new int[(int) (wimg.getWidth() * wimg.getHeight())];

        for (int i = 0; i < wimg.getHeight(); i++) {
            for (int j = 0; j < wimg.getWidth(); j++) {
                Color clr = pxlReader.getColor(j, i);
                if (clr.getHue() >= 25 && clr.getHue() <= 45) {
                    pxlWriter.setColor(j, i, Color.WHITE);
                    imageArray[(int) (wimg.getWidth() * i + j)] = (int) (wimg.getWidth() * i + j);
                } else {
                    pxlWriter.setColor(j, i, Color.BLACK);
                    imageArray[(int) (wimg.getWidth() * i + j)] = -1;
                }
            }
        }
        for (int i = 0; i < wimg.getHeight(); i++) {
            for (int j = 0; j < wimg.getWidth(); j++) {
                System.out.print(imageArray[(int) (wimg.getWidth() * i + j)]);
            }
            System.out.println("");
        }
        UnionFind(img);

        System.out.println("<=====================================(displaying array)=====================================>");
        displayArray(img);
        return wimg;
    }

    /////////////////
    //Union Methods//
    /////////////////

    public static int find(int[] a, int id) {
        if (a[id] < 0)
            return a[id];

        if (a[id] == id)
            return id;

        else return find(a, a[id]);
    }

    public static void union(int[] a, int p, int q) {
        a[find(a,q)] = find(a,p);
    }


    public void UnionFind(Image img) {
        int width = (int) img.getWidth();
        for (int i = 0; i < imageArray.length - 1; i++) {
            if (imageArray[i] != -1) {
                if (i + 1 < imageArray.length && imageArray[i + 1] != -1 && (i + 1) % img.getWidth() != 0)
                    union(imageArray, i, i + 1);
                if (i + width < imageArray.length && imageArray[i + width] != -1) union(imageArray, i, (i + width));
            }
        }
    }

    public void displayArray(Image img) {
        for (int i = 0; i < imageArray.length; i++)
            System.out.print(find(imageArray, i) + ((i + 1) % img.getWidth() == 0 ? "\n" : " "));
    }

    public void setRectangleBoundaries(Image img) {

    }

    ///////////////////
    //Hashing Methods//
    ///////////////////

    public void HashSize(){
        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                hashSize = (int) sizeSlider.getValue();
                System.out.println("size set");
            }
        });
    }

    public void hashing(ActionEvent actionEvent) {
        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                hashSize = (int) sizeSlider.getValue();
                System.out.println("size set");
            }
        });

        HashMap<Integer, List<Integer>> connections = new HashMap<>();
        int minX;
        int maxX;
        int minY;
        int maxY;

        for(int i = 0; i<imageArray.length;i++) {
            if (imageArray[i] != -1) {
                if (connections.containsKey(find(imageArray, i))) {
                    connections.get(find(imageArray, i)).add(i);
                } else {
                    ArrayList<Integer> x = new ArrayList<>();
                    x.add(i);
                    connections.put(find(imageArray, i), x);
                }
            }
        }

        System.out.println("in connections, there are " + connections.size());

        for(int k : connections.keySet()) {
            clusterArray = new ArrayList<>(connections.get(k));

            //connections.get(k).removeIf(c -> clusterArray.size() < 10);
            if(clusterArray.size() > hashSize){
                System.out.println("Key: " + k + ", " + connections.get(k));
                imageDetails.getItems().addAll("Key: " + k + ", cluster size (pxls): " + clusterArray.size());
                clusterLabel.setText(String.valueOf(connections.size()));
                System.out.println("Key: " + k + ", cluster size (pxls): " + clusterArray.size());
            }
        }

        System.out.println("after connections " + connections.size());

        for(int p = 0; p < imageArray.length; p++){
            if(imageArray[p] != -1){
                pixelArray.add(p);
                pixelLabel.setText(String.valueOf(pixelArray.size()));
            }
        }

        System.out.println("creating rectangles");
    }

    ////////
    //Misc//
    ////////

    /*
    sets everything to null as to reset the page
     */
    public void removeImage(ActionEvent actionEvent) {
        imageViewer1.setImage(null);
        imageViewer2.setImage(null);
        imageDetails.getItems().clear();
        clusterLabel.setText(String.valueOf(0));
        pixelLabel.setText(String.valueOf(0));
        System.out.println("Cleared!");
    }

    public void exitApplication(ActionEvent actionEvent) {
        System.out.println("Exiting...");
        System.exit(0);
    }

    public void changeColour(MouseEvent m) {
        //WritableImage newWimg = new WritableImage(img.getPixelReader(), (int) img.getWidth(), (int) img.getHeight());
        //PixelWriter pxlWriter = newWimg.getPixelWriter();
        //PixelReader pxlReader = img.getPixelReader();
        int X = (int) m.getX();
        int Y = (int) m.getY();
        System.out.println("mouse clicked at: " + X + "," + Y);
    }

    /*
    runs method for specified fruit
    */
    public void chooseBlueberry(ActionEvent actionEvent) {
        imageViewer2.setImage(convertToBlackAndWhiteBlueberry(imageViewer1.getImage()));
    }

    public void chooseStrawberry(ActionEvent actionEvent) {
        imageViewer2.setImage(convertToBlackAndWhiteStrawberry(imageViewer1.getImage()));
    }

    public void chooseOrange(ActionEvent actionEvent) {
        imageViewer2.setImage(convertToBlackAndWhiteOrange(imageViewer1.getImage()));
    }
}