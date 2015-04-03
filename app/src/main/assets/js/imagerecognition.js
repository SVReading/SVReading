var World = {
    loaded: false,

    init: function initFn() {
        this.createOverlays();
    },

    createOverlays: function createOverlaysFn() {
        AR.logger.debug("Entering createOverlays");

        /*
        * Initialize the first tracker. This tracker tracks the book covers contained in the given
        * wtc file.
        */
        AR.logger.debug("Initializing tracker");
        this.tracker = new AR.Tracker("images/BookCovers.wtc", {
            onLoaded: function worldLoaded() {
                //The file was loaded
                AR.logger.debug("WTC file loaded!");
            }
        });

        /*
        * Initialize a Trackable2DObject with a wild card so it will recognize any item in the loaded
        * wtc file (who ever came up with this api is brilliant). When an item is recognized, it calls
        * onEnterFieldOfVision.
        */
        AR.logger.debug("Initializing trackable object");
        var pageOne = new AR.Trackable2DObject(this.tracker, "*", {
            onEnterFieldOfVision:
                 function openNewWTC(targetName) {
                        targetName = String(targetName);
                        //We got a hit!
                        AR.logger.debug("Opening WTC file for: " + targetName);

                        //Try to load the wtc file matching the book name
                        var path = "images/";
                        path = path.concat(targetName, ".wtc");
                        AR.logger.debug(path);
                        var lol = String(path);
                        this.bookTracker = new AR.Tracker(lol, {
                            onLoaded: function bookWTCLoaded() {
                                AR.logger.debug("WTC file for specific book loaded!");
                            }
                        });

                        //Set up a new wildcard trackable object for the book pages
                        var trackableBook = new AR.Trackable2DObject(this.bookTracker, "*", {
                            onEnterFieldOfVision: function pageRecognized(targetName) {
                                //We got a hit for a book page
                                AR.logger.debug("Recognized book page " + targetName + "!");

                                //TODO: Call back to android to get the corresponding youtube link from the json file
                                open("android-app://com.google.youtube/http/www.youtube.com/watch?v=Iu0VTI-ZSHQ");
                            }
                        });
                 }
        });
    }
};

World.init();