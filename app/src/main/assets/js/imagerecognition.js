var World = {
	loaded: false,

	init: function initFn() 
	{
		this.createOverlays();
	},

	createOverlays: function createOverlaysFn()
	 {
		AR.logger.debug ("Entering createOverlays");
		/*
			First an AR.Tracker needs to be created in order to start the recognition engine. It is initialized with a URL specific to the target collection. Optional parameters are passed as object in the last argument. In this case a callback function for the onLoaded trigger is set. Once the tracker is fully loaded the function worldLoaded() is called.
			Important: If you replace the tracker file with your own, make sure to change the target name accordingly.
			Use a specific target name to respond only to a certain target or use a wildcard to respond to any or a certain group of targets.
		*/
		AR.logger.debug ("Initializing tracker");
		this.tracker = new AR.Tracker("images/CliffordTest.wtc", 
		{
			onLoaded: this.worldLoaded
		});

		/*
			The next step is to create the augmentation. In this example an image resource is created and passed to the AR.ImageDrawable. A drawable is a visual component that can be connected to an IR target (AR.Trackable2DObject) or a geolocated object (AR.GeoObject). The AR.ImageDrawable is initialized by the image and its size. Optional parameters allow for position it relative to the recognized target.
		*/

		/* Create overlay for page one */
		AR.logger.debug ("Creating overlay one");
		var imgOne = new AR.ImageResource("images/Clifford.jpg");
		var overlayOne = new AR.ImageDrawable(imgOne, 1, {
			offsetX: 0,
			offsetY: 0
		});

		/*
			The last line combines everything by creating an AR.Trackable2DObject with the previously created tracker, the name of the image target and the drawable that should augment the recognized image.
			Please note that in this case the target name is a wildcard. Wildcards can be used to respond to any target defined in the target collection. If you want to respond to a certain target only for a particular AR.Trackable2DObject simply provide the target name as specified in the target collection.
		*/


		AR.logger.debug ("Initializing trackable object");
		var pageOne = new AR.Trackable2DObject(this.tracker, "Clifford", {
			onEnterFieldOfVision :
                function openLinkFn() {
                    AR.logger.debug ("Opening Link!");
                    open("android-app://com.google.youtube/http/www.youtube.com/watch?v=Iu0VTI-ZSHQ");
                }
		});

		AR.logger.debug ("Leaving createOverlays");
	},

	worldLoaded: function worldLoadedFn()
	 {
		AR.logger.debug ("Entering worldLoaded");

		AR.logger.debug ("Leaving worldLoaded");
	 }
};
// end of the world variable
World.init();

function test () 
{
	document.location = "architectsdk://NEEDTHIS";
}

function changeToBookCovers() 
{
		
		// AR.logger.debug ("Initializing tracker");
		// this.tracker = new AR.Tracker("images/BookCovers.wtc", 
		// {
		// 	onLoaded: World.worldLoaded
		// });

		

		// /* Create overlay for page one */
		// AR.logger.debug ("Creating overlays");

		// var imgOne = new AR.ImageResource("images/Clifford.jpg");

		// var overlayOne = new AR.ImageDrawable(imgOne, 1, 
		// {
		// 	offsetX: 0,
		// 	offsetY: 0
		// });

		


		// AR.logger.debug ("Initializing trackable object");
		// var pageOne = new AR.Trackable2DObject(this.tracker, "Clifford", {
		// 	onEnterFieldOfVision :
  //               function openLinkFn() {
  //                   AR.logger.debug ("Opening Link!");
  //                   open("android-app://com.google.youtube/http/www.youtube.com/watch?v=ZZ5LpwO-An4");
  //               }
		// });

		// AR.logger.debug ("Leaving createOverlays");
}
