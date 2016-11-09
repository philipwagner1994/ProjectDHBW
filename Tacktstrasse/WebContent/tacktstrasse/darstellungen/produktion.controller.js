sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/json/JSONModel',
		"sap/ui/core/routing/History"

	], function(jQuery, Controller, JSONModel, History ) {
	"use strict";
 
	var PageController = Controller.extend("sap.checkmarx.selfservice.tacktstrasse.darstellungen.produktion", {
		
		onInit : function (evt) {
			var oView = this.getView();
			oView.byId("LayoutForMap").addContent(new sap.ui.core.HTML( "adminMapHtml", {
                content : "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><title></title>" +
                "</head>" +
                "<body>" +
                "<canvas id='myCanvas' width='700' height='500'></canvas>" + //create the canvas div
                "<div id='overlay'>" + //the overlay div, used as a pop-up menu
                "<div id='popup-menu'></div>" + //the initial content of the pop-up menu
                "</div>" +
                "<div id='feedbackPanel'></div>"  +
                "</body>" +
                "</html>"
         }));

			
            //var canvas = this.oView.byId('myCanvas');
            var canvas = document.getElementById("myCanvas");
            console.log(canvas);
           var ctx = canvas.getContext('2d');
            console.log(ctx);
            
            var imageObj = new Image();

            imageObj.onload = function() {
            	ctx.drawImage(imageObj, 100, 75);
            };
            imageObj.src = "img/Tacktstrasse.JPG";

           /* var canvas = sap.ui.getCore().byId('myCanvas');
            //var ctx = this.getElementById('myCanvas');
            console.log(canvas);
            var context = canvas.getContext("2d");
            var imageObj = new Image();

            imageObj.onload = function() {
              context.drawImage(imageObj, 100, 75);
            };
            imageObj.src = "img/Tacktstrasse.JPG";
            
            
            /*oController.locationTypesModel = new sap.ui.model.json.JSONModel();
            oController.locationsByFloorModel = new sap.ui.model.json.JSONModel();
            if(gDataModel == ""){
                   oModel = new sap.ui.model.json.JSONModel();
                   oView.setModel(oModel);
                   sap.ui.campusconnect.util.UIUtils.loadRestDataSync(oModel, gServiceURL+"/b/api/campusservice/allcampuses");
            }*/
            /*oView.byId("LayoutForMap").addContent(new sap.ui.core.HTML( "adminMapHtml", {
                   content : "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><title></title>" +
                   "</head>" +
                   "<body>" +
                   "<canvas id='myCanvas' width='700' height='500'></canvas>" + //create the canvas div
                   "<div id='overlay'>" + //the overlay div, used as a pop-up menu
                   "<div id='popup-menu'></div>" + //the initial content of the pop-up menu
                   "</div>" +
                   "<div id='feedbackPanel'></div>"  +
                   "</body>" +
                   "</html>"
            }));*/
            /*sap.ui.core.UIComponent.getRouterFor(this).attachRouteMatched(function(oEvent) {
                // when detail navigation occurs, update the binding context
                if (oEvent.getParameter("name") === "location") {

                       var sCampusPath = "/" + oEvent.getParameter("arguments").campus;
                       var sBuilding_id = oEvent.getParameter("arguments").building;
                       var sBuildingPath = "/" + sBuilding_id;
                       var sFloor_id = oEvent.getParameter("arguments").floor;
                       var sFloorPath = "/" + sFloor_id;
                       oController.campus_id = sCampusPath.substring(sCampusPath.indexOf("/") + 1, sCampusPath.length);
                       oView.bindElement(sCampusPath+"/buildings"+sBuildingPath+"/floors"+sFloorPath);  
                       
                       var aData = oView.getModel().getData();
                       oController.floorId = aData[oController.campus_id].buildings[sBuilding_id].floors[sFloor_id].id;
                       oController.buildingId = aData[oController.campus_id].buildings[sBuilding_id].id;
                       oController.loadMap(oController.floorId, oController.buildingId);
                       
                       if(sap.ui.Device.system.desktop){
                              oView.byId("LayoutLocationTable").setWidth("50%");
                       }
                }
         }, this);*/
         
            //oController.loadMap();
//       sap.ui.getCore().getControl("BuildingsTable").removeSelections();
         
   },

            
            loadMap : function(){
            	
                  
               
     },
     
     /*drawMap : function(){
              var _this = this;
              
//            this.locationByFloorModel = new sap.ui.model.json.JSONModel();
              //sap.ui.campusconnect.util.UIUtils.loadRestDataSync(_this.locationsByFloorModel, gServiceURL+"/b/api/locationservice/adminFloorLocations?floorId=" + sFloorId);
              
              var canvas = document.getElementById('myCanvas');
              
              var ctx =  canvas.getContext('2d');
              var popUp = document.getElementById('overlay');
              ctx.fillStyle = 'red';
              var locations = [];
              var img = new Image();
//            img.setSrc("pinMap_64.png");
              img.src = "img/pinMap_64.png";

              for(var i = 0; i < _this.locationsByFloorModel.oData.length; i++) {
                     var newLocation = new Object();
                     newLocation.attributes = [];
                     newLocation.id = _this.locationsByFloorModel.oData[i].id;
                     newLocation.name = _this.locationsByFloorModel.oData[i].locationName;
                     newLocation.x = _this.locationsByFloorModel.oData[i].xCoordinate * gWidthOfPdf;
                     newLocation.x = Math.round(newLocation.x);
                     newLocation.y = _this.locationsByFloorModel.oData[i].yCoordinate * gHeightOfPdf;
                     newLocation.y = Math.round(newLocation.y);
                     newLocation.description = _this.locationsByFloorModel.oData[i].locationDescription;
                     //Location type, as of now there can only be one type for each location
                     newLocation.typeId = _this.locationsByFloorModel.oData[i].type.id;
                     
                     //Load the attributes for each location
                     for(var j = 0; j < _this.locationsByFloorModel.oData[i].attributeValues.length; j++) {
                            var newAttribute = new Object();
                            newAttribute.id = _this.locationsByFloorModel.oData[i].attributeValues[j].typeAttribute.id;
                            newAttribute.value = _this.locationsByFloorModel.oData[i].attributeValues[j].value;
                            newLocation.attributes.push(newAttribute);
//                          delete newAttribute;
                     }
                     
                     newLocation.shown = false;
                     locations.push(newLocation);
//                   delete newLocation;

                     var widthOfPin = img.width;
                     var heightOfPin = img.height;

//                   ctx.drawImage(img, 0, 0, 70, 70, newLocation.x - gPinOffsetX, newLocation.y - gPinOffsetY, 40, 40);
              }
              
              img.onload = function(){
                     for (var i = 0; i < locations.length; i++) {
                            ctx.drawImage(img, 0, 0, 70, 70, locations[i].x - gPinOffsetX, locations[i].y - gPinOffsetY, 40, 40);
                     }
              };
              
              canvas.addEventListener('click', function (event) {
                     var hit = false;
                     var isTouchSupported = 'ontouchstart' in window;
                     
                     var mouseX;
                     var mouseY;
                     
                     if(event.offsetX || event.offsetX == 0) { //chrome & safari
                            mouseX = event.offsetX;
                            mouseY = event.offsetY;
                     } else if(event.layerX || event.layerX == 0) { //mozilla
                            mouseX = event.layerX;
                            mouseY = event.layerY;
                     } else if(isTouchSupported) { //touch
                            mouseX = event.clientX - containerX;
                            mouseY = event.clientY - containerY;
                     } else { //IE
                            mouseX = event.clientX;
                            mouseY = event.clientY;
                     }
                     
                     sap.ui.campusconnect.util.UIUtils.loadRestDataSync(_this.locationsByFloorModel, gServiceURL+"/b/api/locationservice/adminFloorLocations?floorId=" + sFloorId);
                     
                     var aLocations = []

                     for(var i = 0; i < _this.locationsByFloorModel.oData.length; i++) {
                            var newLocation = new Object();
                            newLocation.attributes = [];
                            newLocation.id = _this.locationsByFloorModel.oData[i].id;
                            newLocation.name = _this.locationsByFloorModel.oData[i].locationName;
                            newLocation.x = _this.locationsByFloorModel.oData[i].xCoordinate * gWidthOfPdf;
                            newLocation.x = Math.round(newLocation.x);
                            newLocation.y = _this.locationsByFloorModel.oData[i].yCoordinate * gHeightOfPdf;
                            newLocation.y = Math.round(newLocation.y);
                            newLocation.description = _this.locationsByFloorModel.oData[i].locationDescription;
                            //Location type, as of now there can only be one type for each location
                            newLocation.typeId = _this.locationsByFloorModel.oData[i].type.id;
                            
                            //Load the attributes for each location
                            for(var j = 0; j < _this.locationsByFloorModel.oData[i].attributeValues.length; j++) {
                                   var newAttribute = new Object();
                                   newAttribute.id = _this.locationsByFloorModel.oData[i].attributeValues[j].typeAttribute.id;
                                   newAttribute.value = _this.locationsByFloorModel.oData[i].attributeValues[j].value;
                                   newLocation.attributes.push(newAttribute);
//                                 delete newAttribute;
                            }
                            
                            newLocation.shown = false;
                            aLocations.push(newLocation);
                     }

                     for (var i = 0; i < aLocations.length; i++) {
                            if(mouseX > aLocations[i].x - gPinOffsetX/2 && mouseX < aLocations[i].x + gPinOffsetX/2 && mouseY > aLocations[i].y - gPinOffsetY && mouseY < aLocations[i].y + 5) {
                                   hit = true;
                                          _this._getDialog().setTitle(_this.getView().getModel("i18n").getResourceBundle().getText("Dialog.editLocation"));
                                       sap.ui.getCore().getControl("saveLocation").setText(_this.getView().getModel("i18n").getResourceBundle().getText("Button.update"));
                                   sap.ui.getCore().getControl("saveLocation").setVisible(true);
                                         _this.getLocationTypeAttributes(aLocations[i].typeId);
                                          sap.ui.getCore().getControl("locationId").setValue(aLocations[i].id);
                                          sap.ui.getCore().getControl("locationName").setValue(aLocations[i].name);
                                          sap.ui.getCore().getControl("locationDescription").setValue(aLocations[i].description);
                                          sap.ui.getCore().getControl("xCoord").setValue(aLocations[i].x);
                                          sap.ui.getCore().getControl("yCoord").setValue(aLocations[i].y);
                                          sap.ui.getCore().getControl("selectLocationType").setSelectedKey(aLocations[i].typeId);
                                         for(j = 0; j < aLocations[i].attributes.length; j++) {
                                                if(aLocations[i].attributes[j].value == "true") {
                                                       sap.ui.getCore().getControl("locationTypeAttribute").setSelected(true);
                                                }
                                         }
                                         _this._getDialog().open();
                            }
                     }
                     
                     if(hit == false) {
                            _this._getDialog().setTitle(_this.getView().getModel("i18n").getResourceBundle().getText("Dialog.addLocation"));
                            sap.ui.getCore().getControl("saveLocation").setText(_this.getView().getModel("i18n").getResourceBundle().getText("Button.add"));
                            sap.ui.getCore().getControl("saveLocation").setVisible(true);
                            //Change X and Y values here to relative percentages
                            sap.ui.getCore().getControl("xCoord").setValue(mouseX);
                            sap.ui.getCore().getControl("yCoord").setValue(mouseY);
                            _this._getDialog().open();
                     }
            });
              
              canvas.addEventListener('mousemove', function (event) {                   
                     var isTouchSupported = 'ontouchstart' in window;
                     var mouseX;
                     var mouseY;
                     
                     if(event.offsetX || event.offsetX == 0) { //chrome & safari
                           mouseX = event.offsetX;
                            mouseY = event.offsetY;
                     } else if(event.layerX || event.layerX == 0) { //mozilla
                            mouseX = event.layerX;
                            mouseY = event.layerY;
                     } else if(isTouchSupported) { //touch
                            mouseX = event.clientX - containerX;
                            mouseY = event.clientY - containerY;
                     } else { //IE
                            mouseX = event.clientX;
                            mouseY = event.clientY;
                     }
                     
                     for (var i = 0; i < locations.length; i++) {
                            ctx.drawImage(img, 0, 0, 70, 70, locations[i].x - gPinOffsetX, locations[i].y - gPinOffsetY, 40, 40);
                     }
              });
        },*/

		onBeforeRendering: function() {
			
			
		},
		onAfterRendering: function() {

		},
		
		onNavBack: function () {
			sap.ui.core.UIComponent.getRouterFor(this).navTo("uebersicht");
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