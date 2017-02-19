jQuery(document).ready(function () {
	  jQuery("#findEventsBtn").click(function() {
    	  findEvents();
    });
});

function findEvents() {
	
    jQuery("#findEventsBtn").prop("disabled", true);
    jQuery("#results").empty();

    jQuery.ajax({
        type: "POST",
        contentType: "application/json",
        url: "events/criteria?category=" +  jQuery("#category").val() + "&location=" + jQuery("#location").val(),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
        	console.log("SUCCESS : ", data);
               
            jQuery(function() {
            	jQuery.each(data, function(i, item) {
                    
            		var thelink = jQuery("<a>",{
            		    text: item.title,
            		    title: item.title,
            		    href: item.url
            		})
            		
            		jQuery("<tr>").append(
                    		jQuery("<td>").append(thelink),
                    		jQuery("<td>").text(item.startTime),
                    		jQuery("<td>").text(item.stopTime),
                    		jQuery("<td>").text(item.description)
                    ).appendTo('#results');
                   
                });
            });
            jQuery("#findEventsBtn").prop("disabled", false);

        },
        error: function (e) {
            console.log("ERROR : ", e);
            jQuery("#findEventsBtn").prop("disabled", false);

        }
    });

}