# SVReading
SVReading is a simple Android application based on the [Wikitude image recognition SDK](http://www.wikitude.com/) that allows users to recognize book covers and pages to bring up signed videos that correspond to the page.

## Download
The easiest method to download and modify the code is through [Android Studio](https://developer.android.com/sdk/index.html) by [checking the project out from version control](https://www.jetbrains.com/idea/help/cloning-a-repository-from-github.html).

If you prefer to use a different IDE or just want the source code, you can also use [Git](http://git-scm.com/) to [clone](http://git-scm.com/book/en/v2/Git-Basics-Getting-a-Git-Repository#Cloning-an-Existing-Repository) the repository:

```bash
git clone https://github.com/SVReading/SVReading.git
```

## Adding More Books
The application is set up with easy extensibility in mind, and adding more books only requires three steps (four if you need to create a wikitude account). The beginning of the two steps are done within [Wikitude's target manager tool](http://www.wikitude.com/developer/tools/target-manager), and the rest of the work is done within Android Studio (or whatever IDE you prefer).

1. Open the "Book Covers" project folder (create one if does not exist yet)
  1. Drag the book cover(s) image to the add image box
  2. Select all of the images within the folder once the new image(s) have been added
  3. Name the target collection **BookCovers** (This is extremely important as the app will not work if this is not correct)
  4. Generate the target collection
  5. Download the new "BookCover.wtc" file as a version 4.0
  6. Replace the file in the Android project which exists in the file path "SVReading/app/src/main/assets/images/BookCovers.wtc"

2. Create a new project folder for the name of the book
  1.  Name the image files in an easy to distiguinsh fashion (naming them their corresponding page number is recommended)
  2.  Add the images to the to the project folder
  3.  Name the target collection the name of the book with underscores (ex. Curious_George_And_The_Dump_Truck)
  4.  Generate the target collection and download the .wtc file as version 4.0
  5.  Add the file to the folder "SVReading/app/src/main/assets/images/"

3. Open the Books.json file in the project folder "SVReading/app/src/main/assets/" 
  1. Following the other books in file add the book name in quotations (**The name must match what you named the .wtc file, case sensitive, without the underscores**)
  2. Adding page support follows a similar pattern with the page name in quotations (**This name must match what you named the image file that corresponds to that page case sensitive**) followed by a colon and then the youtube video identifier (youtube.com/watch?v=**IhNZLSEx2EI**) also in quotations.
  3. Pages that are not supported do not need to be add to the list and the user will be notified that the page is currently not supported
   ```
    "Book Name":
    {
        "Page1" : "IhNZLSEx2EI",
        "2" : "j42ck-cKG0E",
        ...
        "lastpage" : "zXuQgTtUM84"
    }
  ```

Once these changes have been made the [apk can be built from within Android Studio](https://developer.android.com/tools/building/building-studio.html).

##Authors
- **Josh Alley**
- **Kurt Bonatz**
- **Kevin Smith**
- **Mica Alexander**
- **Harrison Obiorah**
