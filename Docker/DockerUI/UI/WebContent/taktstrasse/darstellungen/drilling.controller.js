sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/json/JSONModel',
		"sap/ui/core/routing/History"

	], function(jQuery, Controller, JSONModel, History ) {
	"use strict";
 
	var PageController = Controller.extend("sap.checkmarx.selfservice.taktstrasse.darstellungen.drilling", {
		
		onInit : function (evt) {
			 var oModel = new sap.ui.model.json.JSONModel();
	           // Load JSON in model
	              oModel.loadData("json/chartdrill.json");
			this.getView().setModel(oModel);
			
			this.getRouter().attachRoutePatternMatched(this.onRouteMatched, this);
		},
		onRouteMatched: function() {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			this.getView().byId("Rows").setValue(oConfigModel.config.rows);
			this.getView().byId("CustomerNumSearch").setValue(oConfigModel.config.CustomerNum);
			this.getView().byId("MaterialNumSearch").setValue(oConfigModel.config.MaterialNum);
			
			/*$.ajax({
		    async : false,
		    type : "GET",
		    url : "http://localhost:1234/Server/java",
		    dataType : 'text',
		    data : {
			'function' : "history_drilling",
			history: oConfigModel.config.rows,
			materialno: oConfigModel.config.MaterialNum,
			customerno: oConfigModel.config.CustomerNum
		    },
		    success : function(response) {
		    	if(response != "null"){*/
		    	//var oModel = this.getView().getModel();
		    	//oModel.loadData("json/chart.json");
		    	
		    	/*console.log(oModel.oData);
		    	oModel.getProperty("/lineData/customerno").push("123");
		    	 for(var i=0;i< oModel.oData.Data.length;i++){
		    		 oModel.getProperty("/lineData/customerno").push("123");
		    		 oModel.oData.orderno.push(oModel.oData.Data[i].orderno);
		    		 oModel.oData.materialno.push(oModel.oData.Data[i].materialno);
		    		 oModel.oData.speed.push(oModel.oData.Data[i].speed);
		    		 oModel.oData.temp.push(oModel.oData.Data[i].temp);
		    	     }*/
		    	//var oController = this;
		    	//var jsonResponse = JSON.parse(response);
		    	//oModel.setProperty("/Data", jsonResponse);
		    	//oModel.refresh(true);
		    	//console.log(oModel);
		    	//}
		    	//oController.getDataUpdate();
		    	
		   /* },
		    error : function(message) {
			console.error("Error");
		    }	
		});*/
		},
		getDataUpdate: function() {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			if(oConfigModel.livedown==false){
			$.ajax({
			    async : true,
			    type : "GET",
			    url : "http://localhost:1234/Server/java",
			    dataType : 'text',
			    data : {
				'function' : "live_drilling",
				materialno: oConfigModel.config.MaterialNum,
				customerno: oConfigModel.config.CustomerNum
			    },
			    success : function(response) {
			    	if(response != "null"){
			    		var that = this;
			    	var oModel = that.getView().getModel();
			    	var oController = this;
			    	
			    	var jsonResponse = JSON.parse(response);
			    	console.log(jsonResponse);
			    	oModel.getProperty("/speedData/speed").push(jsonResponse[0].speed);
			    	oModel.getProperty("/speedData/speed").shift();
			    	oModel.getProperty("/tempData/temp").push(jsonResponse[0].temp);
			    	oModel.getProperty("/tempData/temp").shift();
			    	oModel.getProperty("/tempData/orderno").push(jsonResponse[0].orderno);
			    	oModel.getProperty("/tempData/orderno").shift();
			    	oModel.getProperty("/speedData/orderno").push(jsonResponse[0].orderno);
			    	oModel.getProperty("/speedData/orderno").shift();
			    	oModel.oData.gaugeDatatemp.temp = jsonResponse[0].temp;
			    	oModel.oData.gaugeDataspeed.speed = jsonResponse[0].speed;
			    	
			    	oModel.refresh(true);
			    	that.getView().byId("LineChartHeat").load();
			    	that.getView().byId("LineChartSpeed").load();
			    	that.getView().byId("GaugeChartHeat").load();
			    	that.getView().byId("GaugeChartSpeed").load();
			    	oController.getDataUpdate();
			    	}
			    },
			    error : function(message) {
				console.error("Error");
			    }	
			});
			}
		},
		getRouter : function () {
			return sap.ui.core.UIComponent.getRouterFor(this);
		},
		onRowPress: function () {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			var value = this.getView().byId("Rows").getValue();
			if(value != undefined){
				oConfigModel.config.rows = value;
			}
			else oConfigModel.config.rows = "";
		},
		onCustomerNumSearch: function () {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			var value = this.getView().byId("CustomerNumSearch").getValue();
			if(value != undefined){
				oConfigModel.config.CustomerNum = value;
			}
			else oConfigModel.config.CustomerNum = "";
		},
		onMaterialNumSearch: function () {
			var oConfigModel = sap.ui.getCore().getModel("ConfigModel").getData();
			var value = this.getView().byId("MaterialNumSearch").getValue();
			if(value != undefined){
				oConfigModel.config.MaterialNum = value;
			}
			else oConfigModel.config.MaterialNum = "";
		},
		onNavBack: function () {
			sap.ui.core.UIComponent.getRouterFor(this).navTo("overview");
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