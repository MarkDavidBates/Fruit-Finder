# Fruit-Finder
pixel oriented data structure that uses hash maps to identify clusters

##How it Works
The Program works by taking an image file from the file browser and specifying if that fruit is one of the following:
- Orange
- Strawberry
- Blueberry

It then converts all the pixels that are within the colour hue range and converts them all to white pixels. All other
pixels in the image are converted to balck. The converted image is then presented on the GUI, where the user can now
locate the clusteres of fruits.
Afterwards, the pixels can be made into a 2D array using hash mapping which displays each white pixel as it's own unique
Integer. From here you can sort the Hash Map with a Union Find Method to organise the pixels into their assignedd clusters.

the information gathered shows how many pixels are considered to be the fruits, as well as how many fruit clusters there are.

![alt text](https://github.com/MarkDavidBates/Fruit-Finder/blob/main/images/Fruit%20Finder%20GUI.jpeg)
