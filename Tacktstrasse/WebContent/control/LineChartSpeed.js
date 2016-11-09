sap.ui.define([
               'sap/ui/core/Control'
             ], function(Control) {
               'use strict';

               var CHART_CANVAS_NAME_PREFIX = 'LineChartSpeed';

               return Control.extend('sap.checkmarx.selfservice.control.LineChartSpeed', {
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
             		          type: 'spline'
             		        },
             		        
             		        axis: {
             		          y: {
             		            label: {
             		              text: 'Geschwindigkeit',
             		              position: 'outer-middle'
             		            },
             		            tick: {
             	              format: d3.format("°,") // ADD
             		            }
             		          },
             		          x: {
             			            label: {
             			              text: 'Auftrag',
             			              position: 'outer-middle'
             			            },
             		          }
             			           
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
                   oRm.addClass("LineChartSpeed");
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
                 }
               });
             });