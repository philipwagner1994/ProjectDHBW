;(function () {
    "use strict";
 
 

 
 
    sap.ui.core.Control.extend("sap.checkmarx.selfservice.control.circle", {
 
        metadata: {
            properties : {
                radius   : {type : "int", defaultValue: 50}
            },
            //defaultAggregation : "...",
            aggregations : { },
            associations : { },
            events       : { }
        },
        
        init : function(){ },
 
        onAfterRendering: function (oEvent){
            var jqContent, svg, radius;
            
            radius = this.getRadius();
            if (radius <10){
                radius = 10;
            }
            
            //HINT: jQuery(this.getDomRef()) and this.$() is equal - it gives you the jQuery object for this control's DOM element
            svg = d3.select(this.getDomRef()).append("svg")
                        .attr({
                            "class" : "circleSvg",    
                            "width" : 250,
                            "height": 250
                         });
            svg.append("circle").attr({
                cx : 150,
                cy : 100,
                r  : radius
            })
            .style("fill","lightblue");
            
            d3.selectAll(".circleSvg")
            /*.on("mouseover", function(d) {		
            div.transition()		
                .duration(200)		
                .style("opacity", .9);		
            div	.html(formatTime(d.date) + "<br/>"  + d.close)	
                .style("left", (d3.event.pageX) + "px")		
                .style("top", (d3.event.pageY - 28) + "px");	
            })					
        .on("mouseout", function(d) {		
            div.transition()		
                .duration(500)		
                .style("opacity", 0);	
        });*/
            .on("mouseover", function() {
                d3.select(this).style("stroke","black");
                //currentObject = this;
            })
            .on("mouseout", function() {
                d3.select(this).style("stroke","none");
                //currentObject = null;
            });
            
 
        },
 
        renderer : {
 
            render : function(rm, oControl) {
                rm.write("<div");
                rm.writeControlData(oControl);
                rm.addClass("circle");
                rm.writeClasses();
                rm.write(">");
                rm.write("</div>");
            }
        }
    });
 
}());