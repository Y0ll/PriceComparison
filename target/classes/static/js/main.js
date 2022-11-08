$(function(){

    $("#all-request").click(function()
    {
        $.get('/price/', function(response)
        {
            $("#products").empty();
            $('#products').append('<div id="prods">' );
            for (i in response)
            {

                 $('#products').append('<a href = "' + response[i][5] + '"  target="_blank" >' + response[i][0] + ' '  + response[i][1] + ' ' + response[i][2] + ' '+ response[i][3] + ' '+ response[i][4] +'</a>' + '<img src = "' + response[i][6] + '" width="100" height="100" >'  +  "<br>");
            }
            $('#products').append('</div>' );

        });
    });

     $("#all-request-DNS").click(function()
        {
            $.get('/priceDNS/', function(response)
            {
                $("#products").empty();
                $('#products').append('<div id="prods">' );
                for (i in response)
                {

                 $('#products').append('<a href = "' + response[i][5] + '"  target="_blank" >' + response[i][0] + ' '  + response[i][1] + ' ' + response[i][2] + ' '+ response[i][3] + ' '+ response[i][4] +'</a>' + '<img src = "' + response[i][6] + '" width="100" height="100" >'  +  "<br>");
                }
                $('#products').append('</div>' );

            });
        });

     $("#all-request-MVIDEO").click(function()
         {
             $.get('/priceMVIDEO/', function(response)
             {
                 $("#products").empty();
                 $('#products').append('<div id="prods">' );
                 for (i in response)
                 {

                 $('#products').append('<a href = "' + response[i][5] + '"  target="_blank" >' + response[i][0] + ' '  + response[i][1] + ' ' + response[i][2] + ' '+ response[i][3] + ' '+ response[i][4] +'</a>' + '<img src = "' + response[i][6] + '" width="100" height="100" >'  +  "<br>");
                 }
                 $('#products').append('</div>' );

             });
         });

     $("#in-stock").click(function()
        {
            $.get('/priceInStock/', function(response)
            {
                $("#products").empty();
                $('#products').append('<div id="prods">' );
                for (i in response)
                {

                 $('#products').append('<a href = "' + response[i][5] + '"  target="_blank" >' + response[i][0] + ' '  + response[i][1] + ' ' + response[i][2] + ' '+ response[i][3] + ' '+ response[i][4] +'</a>' + '<img src = "' + response[i][6] + '" width="100" height="100" >'  +  "<br>");
                }
                $('#products').append('</div>' );

            });
        });

     $("#today").click(function()
        {
            $.get('/priceToDay/', function(response)
            {
                $("#products").empty();
                $('#products').append('<div id="prods">' );
                for (i in response)
                {

                 $('#products').append('<a href = "' + response[i][5] + '"  target="_blank" >' + response[i][0] + ' '  + response[i][1] + ' ' + response[i][2] + ' '+ response[i][3] + ' '+ response[i][4] +'</a>' + '<img src = "' + response[i][6] + '" width="100" height="100" >'  +  "<br>");
                }
                $('#products').append('</div>' );

            });
        });

     $("#yesterday").click(function()
        {
            $.get('/priceYesterday/', function(response)
            {
                $("#products").empty();
                $('#products').append('<div id="prods">');
                for (i in response)
                {

                 $('#products').append('<a href = "' + response[i][5] + '"  target="_blank" >' + response[i][0] + ' '  + response[i][1] + ' ' + response[i][2] + ' '+ response[i][3] + ' '+ response[i][4] +'</a>' + '<img src = "' + response[i][6] + '" width="100" height="100" >'  +  "<br>");
                }
                $('#products').append('</div>' );

            });
        });

      $("#month").click(function()
         {
             $.get('/priceMonth/', function(response)
             {
                 $("#products").empty();
                 $('#products').append('<div id="prods">' );
                 for (i in response)
                 {

                 $('#products').append('<a href = "' + response[i][5] + '"  target="_blank" >' + response[i][0] + ' '  + response[i][1] + ' ' + response[i][2] + ' '+ response[i][3] + ' '+ response[i][4] +'</a>' + '<img src = "' + response[i][6] + '" width="100" height="100" >'  +  "<br>");
                 }
                 $('#products').append('</div>' );

             });
         });
    $('#search-product').click(function()
    {
        var data = $('#product-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/setProduct/',
            data: data,
            success: function(response)
            {
                location.reload(true);
            }
        });
    });

});