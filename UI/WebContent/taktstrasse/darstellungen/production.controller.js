sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/json/JSONModel',
		"sap/ui/core/routing/History"

	], function(jQuery, Controller, JSONModel, History ) {
	"use strict";
 
	var PageController = Controller.extend("sap.checkmarx.selfservice.taktstrasse.darstellungen.production", {
		
		onInit : function (evt) {
			
			var oModel = new sap.ui.model.json.JSONModel();
	           // Load JSON in model
	              oModel.loadData("json/data.json");
			this.getView().setModel(oModel);
			
			var oView = this.getView();
			var pic = oView.byId("pic");
			
			var img = new sap.ui.commons.Image({src: "img/Taktstrasse.JPG"});
			img.setWidth("1300px");
			pic.addContent(img);
			pic.addContent(
					new sap.ui.commons.Image({src: "img/Empty.JPG"}), {left: "510px", top: "400px"});

			var addIcons = function(oPosition, id){
				var icon = new sap.ui.commons.Image();
				icon.setSrc("sap-icon://lightbulb");

				var onPress = function(){
					openAlert(id)
					icon.detachPress(onPress);
					
				};
				icon.attachPress(onPress);
				pic.addContent(icon, oPosition);
				console.log(icon);
			};
			function openAlert(id) {
					sap.ui.commons.MessageBox.alert("Number "+oModel.oData.TileCollection[id].number+"\n"+
													"Unit "+oModel.oData.TileCollection[id].numberUnit);
				}

			addIcons({left: "400px", top: "510px"}, 1);
			addIcons({left: "400px", top: "340px"}, 2);
			addIcons({left: "545px", top: "340px"}, 3);
			addIcons({left: "870px", top: "340px"}, 4);
			addIcons({left: "1170px", top: "378px"}, 5);
			
		},     

		onBeforeRendering: function() {
			
			
		},
		onAfterRendering: function() {

		},
		
		onNavBack: function () {
			sap.ui.core.UIComponent.getRouterFor(this).navTo("overview");
		},
		checkCircle: function () {
			console.log("Super");
		}

	});
 
	return PageController;
	


	       
/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf tacktstrasse.uebersicht
*/
//	onInit: function() {
//
//	},

/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf tacktstrasse.uebersicht
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf tacktstrasse.uebersicht
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf tacktstrasse.uebersicht
*/
//	onExit: function() {
//
//	}

});