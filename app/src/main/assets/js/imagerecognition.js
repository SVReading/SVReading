var World = {
	loaded: false,

	init: function initFn() {
		this.createOverlays();
	},

	createOverlays: function createOverlaysFn() {
		AR.logger.debug ("Entering createOverlays");
		/*
			First an AR.Tracker needs to be created in order to start the recognition engine. It is initialized with a URL specific to the target collection. Optional parameters are passed as object in the last argument. In this case a callback function for the onLoaded trigger is set. Once the tracker is fully loaded the function worldLoaded() is called.

			Important: If you replace the tracker file with your own, make sure to change the target name accordingly.
			Use a specific target name to respond only to a certain target or use a wildcard to respond to any or a certain group of targets.
		*/
		/*
			SUPPORT FOR MULTIPLE BOOKS
			Can I use multiple trackers? Array of trackers? NO can have mutliple trackers but only one can be enabled at a time.
			Can I use multiple trackableobjects? Loop connect trackers to trackable objects? YES, but again only for one tracker. This could be used for videos though? Loop through and get videos for each trackable object (book)
			Can I get name of recognized object?  I THINK SO, with property targetName. This could also be used for videos. 
		*/
		AR.logger.debug ("Initializing tracker");
		
		var tracker2 = new AR.Tracker("images/secondlevelclifford.wtc", {
			onLoaded:
			this.worldLoaded
		});
		
		var clifford2 = new AR.Trackable2DObject(tracker2, "cliffordFriends", {
			
			onEnterFieldOfVision :
				function revert2() {
					var en1 = tracker1.enabled;
					var en2 = tracker2.enabled;
					if (en1)
						AR.logger.debug ("tracker 1 is enabled");
					else if (en2)
						AR.logger.debug ("Tracker 2 is enabled");
					else
						AR.logger.debug ("Neither tracker is enabled?");
						
					AR.logger.debug ("Revert after finding - " + clifford2.targetName);
						tracker1.enabled = true;
				}
			
		});
		
		var tracker3 = new AR.Tracker("images/secondleveltext.wtc", {
			onLoaded: this.worldLoaded
		});
		
		var text2 = new AR.Trackable2DObject(tracker3, "textbooks", {
			
			onEnterFieldOfVision :
				function revert() {
						
					AR.logger.debug ("Revert after finding - " + text2.targetName);
						tracker1.enabled = true;
				}
			
		});
		
		var tracker1 = new AR.Tracker("images/firstlevel.wtc", {
			onLoaded: this.worldLoaded
		});

		/*
			The next step is to create the augmentation. In this example an image resource is created and passed to the AR.ImageDrawable. A drawable is a visual component that can be connected to an IR target (AR.Trackable2DObject) or a geolocated object (AR.GeoObject). The AR.ImageDrawable is initialized by the image and its size. Optional parameters allow for position it relative to the recognized target.
		*/

		/* Create overlay for page one */
		AR.logger.debug ("Creating overlay one");
		var imgOne = new AR.ImageResource("images/imageOne.png");
		var overlayOne = new AR.ImageDrawable(imgOne, 1, {
			offsetX: 0,
			offsetY: 0
		});

		/*
			The last line combines everything by creating an AR.Trackable2DObject with the previously created tracker, the name of the image target and the drawable that should augment the recognized image.
			Please note that in this case the target name is a wildcard. Wildcards can be used to respond to any target defined in the target collection. If you want to respond to a certain target only for a particular AR.Trackable2DObject simply provide the target name as specified in the target collection.
		*/
		/*
		
			Can I load new trackers dynamically upon a hit of a target?
			Find out what * is giving me.
		
		*/
		AR.logger.debug ("Initializing trackable object");
		var clifford = new AR.Trackable2DObject(tracker1, "clifford", {
			
			onEnterFieldOfVision :
				function opentracker2() {
					AR.logger.debug ("Opening Tracker 2 - " + clifford.targetName);
						tracker2.enabled = true;
				}
			
		});
		
		AR.logger.debug ("Initializing trackable object");
		var text = new AR.Trackable2DObject(tracker1, "*", {
			
			onEnterFieldOfVision :
				function opentracker3() {
					AR.logger.debug ("Opening Tracker 3 - " + text.targetName);
						tracker3.enabled = true;
				}
			
		});
		
		
		AR.logger.debug ("Leaving createOverlays");
	},

	worldLoaded: function worldLoadedFn() {
		AR.logger.debug ("Entering worldLoaded");
		var cssDivLeft = " style='display: table-cell;vertical-align: middle; text-align: right; width: 50%; padding-right: 15px;'";
		document.getElementById('loadingMessage').innerHTML =
			"<div" + cssDivLeft + ">Please scan book page or cover.</div>";

		// Remove Scan target message after 10 sec.
		setTimeout(function() {
			var e = document.getElementById('loadingMessage');
			e.parentElement.removeChild(e);
		}, 100000);
		
		AR.logger.debug ("Leaving worldLoaded");
	}
};

World.init();
