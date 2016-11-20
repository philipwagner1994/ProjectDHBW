sap.ui.define([
               'sap/ui/core/Control'
             ], function(Control) {
               'use strict';

               var CHART_CANVAS_NAME_PREFIX = 'GaugeChartSpeed';

               return Control.extend('sap.checkmarx.selfservice.control.GaugeChartSpeed', {
                 metadata: {
                   properties: {
                     data: {
                       type: 'object'
                     }
                   },
                   events: {
                     unload: {
                       enablePreventDefault: true,
                       chartIDs: {
                         type: 'string[]'
                       }
                     }
                   }
                 },

                 init: function() {
                   var _newCustomChart;
                 },

                 onBeforeRendering: function() {

                 },

                 onAfterRendering: function() {
                   var chartData = this.getData();
                   
                   // required due to lifecycle calls > init of undefined vars
                   if (chartData === undefined) {
                     return;
                   }
                   
                   this._newCustomChart = c3.generate({
                	   bindto: '#' + CHART_CANVAS_NAME_PREFIX + this.getId(),
                	   data: {
                		  json: chartData,
                		  type: 'gauge'
                		        //onclick: function (d, i) { console.log("onclick", d, i); },
                		        //onmouseover: function (d, i) { console.log("onmouseover", d, i); },
                		        //onmouseout: function (d, i) { console.log("onmouseout", d, i); }
                		    },
                		    gauge: {
               		        label: {
                		            format: function(value, ratio) {
               		                return value;
                		            },
                		            show: true // to turn off the min/max labels.
                		        },
                		    min: 0, // 0 is default, //can handle negative min e.g. vacuum / voltage / current flow / rate of change
                		    max: 20000, // 100 is default
                		    units: 'm/s',
                		    width: 39 // for adjusting arc thickness
                		    },
                		    color: {
                		        pattern: ['#60B044', '#F6C600', '#F97600','#FF0000'], // the three color levels for the percentage values.
                		        threshold: {
                		            unit: 'm/s', // percentage is default
//                		            max: 200, // 100 is default
                		            values: [5000, 10000, 15000, 20000]
                		        }
                		    },
                		    size: {
                		        height: 180,
                		        width: 600
                		    }
                		});
                 },

                 exit: function() {
                   this._newCustomChart.destroy();
                 },

                 renderer: function(oRm, oControl) {
                   //var oBundle = oControl.getModel('i18n').getResourceBundle();

                   //Create the control
                   oRm.write('<div');
                   oRm.writeControlData(oControl);
                   oRm.addClass("GaugeChartSpeed");
                   oRm.addClass("sapUiResponsiveMargin");
                   oRm.writeClasses();
                   oRm.write('>');

                   oRm.write('<div id="' + CHART_CANVAS_NAME_PREFIX + oControl.getId() + '"></div>');

                   oRm.write('</div>');
                 },

                 unload: function(chartIDs) {
                   this._newCustomChart.unload({
                     ids: chartIDs
                   });
                 },
                 load: function(){
                   	 var chartData = this.getData();
                   	 this._newCustomChart.load({
                	          json: chartData,
                   	 })
                   }
               });
             });