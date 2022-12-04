$(function(){

    $("#all-request").click(function()
    {
        $.get('/price/', function(response)
        {
            $("#products").empty();
            $('#products').append('<div id="prods">' );
            for (i in response)
            {
                 $('#products').append('<a href = "' + response[i][5] + '" target="_blank" >' + response[i][0] + '</a>'  + "<p> Цена: " + response[i][1] + '</p>'  + '<p>' + response[i][2] + '</p>' + "<p> Магазин: " + response[i][3] + '</p>'  + '<p> Дата запроса:' + response[i][4] + '</p>' + '<img src = "' + response[i][6] + '" width="100" height="100" >' + "<br> <br>");
             }
            $('#products').append('</div>' );

        });
    });

    $("#all-request-sort").click(function()
        {
            $.get('/priceSort/', function(response)
            {
                $("#products").empty();
                $('#products').append('<div id="prods">' );
                for (i in response)
                {

                 $('#products').append('<a href = "' + response[i][5] + '" target="_blank" >' + response[i][0] + '</a>'  + "<p> Цена: " + response[i][1] + '</p>'  + '<p>' + response[i][2] + '</p>' + "<p> Магазин: " + response[i][3] + '</p>'  + '<p> Дата запроса:' + response[i][4] + '</p>' + '<img src = "' + response[i][6] + '" width="100" height="100" >' + "<br> <br>");                }
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

                 $('#products').append('<a href = "' + response[i][5] + '" target="_blank" >' + response[i][0] + '</a>'  + "<p> Цена: " + response[i][1] + '</p>'  + '<p>' + response[i][2] + '</p>' + "<p> Магазин: " + response[i][3] + '</p>'  + '<p> Дата запроса:' + response[i][4] + '</p>' + '<img src = "' + response[i][6] + '" width="100" height="100" >' + "<br> <br>");
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

                 $('#products').append('<a href = "' + response[i][5] + '" target="_blank" >' + response[i][0] + '</a>'  + "<p> Цена: " + response[i][1] + '</p>'  + '<p>' + response[i][2] + '</p>' + "<p> Магазин: " + response[i][3] + '</p>'  + '<p> Дата запроса:' + response[i][4] + '</p>' + '<img src = "' + response[i][6] + '" width="100" height="100" >' + "<br> <br>");
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

                 $('#products').append('<a href = "' + response[i][5] + '" target="_blank" >' + response[i][0] + '</a>'  + "<p> Цена: " + response[i][1] + '</p>'  + '<p>' + response[i][2] + '</p>' + "<p> Магазин: " + response[i][3] + '</p>'  + '<p> Дата запроса:' + response[i][4] + '</p>' + '<img src = "' + response[i][6] + '" width="100" height="100" >' + "<br> <br>");
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

                 $('#products').append('<a href = "' + response[i][5] + '" target="_blank" >' + response[i][0] + '</a>'  + "<p> Цена: " + response[i][1] + '</p>'  + '<p>' + response[i][2] + '</p>' + "<p> Магазин: " + response[i][3] + '</p>'  + '<p> Дата запроса:' + response[i][4] + '</p>' + '<img src = "' + response[i][6] + '" width="100" height="100" >' + "<br> <br>");
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

                 $('#products').append('<a href = "' + response[i][5] + '" target="_blank" >' + response[i][0] + '</a>'  + "<p> Цена: " + response[i][1] + '</p>'  + '<p>' + response[i][2] + '</p>' + "<p> Магазин: " + response[i][3] + '</p>'  + '<p> Дата запроса:' + response[i][4] + '</p>' + '<img src = "' + response[i][6] + '" width="100" height="100" >' + "<br> <br>");
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

                 $('#products').append('<a href = "' + response[i][5] + '" target="_blank" >' + response[i][0] + '</a>'  + "<p> Цена: " + response[i][1] + '</p>'  + '<p>' + response[i][2] + '</p>' + "<p> Магазин: " + response[i][3] + '</p>'  + '<p> Дата запроса:' + response[i][4] + '</p>' + '<img src = "' + response[i][6] + '" width="100" height="100" >' + "<br> <br>");
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
                alert('asdasd');
            }
        });
    });

});