function httpGet(code){
    var url = "https://restcountries.eu/rest/v2/alpha/" + code + "?fields=alpha2Code";
    var con = new java.net.URL(url).openConnection();
    con.requestMethod = "GET";

    return asResponse(con);
}


function asResponse(con){
    var d = read(con.inputStream);

    return {data : d, statusCode : con.responseCode};
}

function read(inputStream){
    var inReader = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream));
    var inputLine;
    var response = new java.lang.StringBuffer();

    while ((inputLine = inReader.readLine()) != null) {
           response.append(inputLine);
    }
    inReader.close();
    return response;
}

function validate($this) {
	var results = [];
	var p = TermFactory.namedNode("http://example.org/ns#check");
	var s = $data.find($this, p, null);
	for(var t = s.next(); t; t = s.next()) {
		var object = t.object;
		var result = httpGet(object.lex);

		if(object.lex != JSON.parse(result.data).alpha2Code) {
			results.push({
				value : object
			});
		}
	}
	return results;
}