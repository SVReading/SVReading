var World = {
    loaded: false,

    init: function initFn() {
        this.createOverlays();
    },

    createOverlays: function createOverlaysFn() {
        AR.logger.debug("Entering createOverlays");

        /*
         * Initialize the first tracker. This tracker tracks the book covers contained in the given
         * wtc file. The tracker calls the function onLoaded when it is done loading.
         */
        AR.logger.debug("Initializing tracker");
        var coverTracker = new AR.Tracker("images/BookCovers.wtc", {
            onLoaded: this.initUI
        });

        /*
         * Initialize a Trackable2DObject with a wild card so it will recognize any item in the loaded
         * wtc file (who ever came up with this api is brilliant). When an item is recognized, it calls
         * onEnterFieldOfVision.
         */
        AR.logger.debug("Initializing trackable object");
        var pageOne = new AR.Trackable2DObject(coverTracker, "*", {
            onEnterFieldOfVision: function openNewWTC(targetName) {
                bookName = String(targetName);
                //We got a hit!
                AR.logger.debug("Opening WTC file for: " + bookName);

                //Try to load the wtc file matching the book name
                var path = "images/";
                path = path.concat(bookName, ".wtc");
                AR.logger.debug(path);

                /*
                * Initialize another track which pauses the book cover tracker. This tracker tracks
                * the individual book pages of the book whose book cover we recognized.
                */
                this.bookTracker = new AR.Tracker(String(path), {
                    onLoaded: function bookWTCLoaded() {
                        AR.logger.debug("WTC file for specific book loaded!");
                        //Change div text field in the html file to show book currenlty being tracked
                        document.getElementById('loadingMessage').innerHTML = "<div>" + bookName.replace("_", " ") + "</div>";
                    }
                });

                /*
                * Set up a new wildcard trackable object for the pages of the book currently being tracked.
                * This trackable object will handle bringing up the youtube video of the book cover
                * if one exists.
                */
                var trackableBook = new AR.Trackable2DObject(this.bookTracker, "*", {
                    onEnterFieldOfVision: function pageRecognized(targetName) {
                        //We got a hit for a book page
                        pageNum = targetName;
                        AR.logger.debug("Recognized book page " + pageNum + "!");

                        //Setting the location communicates back to Android via a urlListener
                        document.location = "architectsdk://" + bookName + "," + pageNum;
                    }
                });
            }
        });
    },

    /*
    * Initialize the UI
    */
    initUI: function initUI() {
        AR.logger.debug("Entering worldLoaded");
        var cssDivLeft = " style='display: table-cell;vertical-align: middle; text-align: right; width: 50%; padding-right: 15px;'";
        document.getElementById('loadingMessage').innerHTML = "<div >Please Scan a Book Cover</div>";

        AR.logger.debug("Leaving worldLoaded");
    }
};

World.init();

/*
* Restart the script when a user wants to recognize a new book cover when one has already been
* recognized
*/
function changeToCover() {
    World.init();
}