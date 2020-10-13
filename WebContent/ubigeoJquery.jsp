<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>
<title>Ubigeo - Sede Norte - Jorge Jacinto</title>
</head>
<body>

<div class="container">
<h1>Ubigeo</h1>

	<form action="registraCompu" id="id_form"> 
			<div class="form-group">
				<label class="control-label" for="id_departamento">DEPARTAMENTO</label>
				<select id="id_departamento" name="" class='form-control'>
					<option value=" ">[Seleccione Departamento]</option>    
				</select>
		    </div>
		   	<div class="form-group">
				<label class="control-label" for="id_provincia">PROVINCIA</label>
				<select id="id_provincia" name="" class='form-control'>
					<option value=" ">[Seleccione Provincia]</option>    
				</select>
		    </div>
		   <div class="form-group">
				<label class="control-label" for="id_distrito">DISTRITO</label>
				<select id="id_distrito" name="" class='form-control'>
					<option value=" ">[Seleccione Distrito]</option>    
				</select>
		    </div>

	</form>
</div>

<script type="text/javascript">
		
	$.getJSON("cargaUbigeo",{}, function(data){
		console.log(data);
		
		$.each(data, function(i, obj){
			$("#id_departamento").append("<option value='" + obj.departamento + "'>"+ obj.departamento +"</option>");
		});
		
	});
	
	
	$("#id_departamento").change(function(){
		
		var var_dep = $("#id_departamento").val();
		console.log(var_dep);
		
		$("#id_provincia").empty();
		$("#id_provincia").append("<option value=' '>[Seleccione Provincia]</option>");
		
		$("#id_distrito").empty();
		$("#id_distrito").append("<option value=' '>[Seleccione Distrito]</option>");

		$.getJSON("cargaUbigeo",{"departamento":var_dep}, function(data){
			
			$.each(data, function(i, obj){
				$("#id_provincia").append("<option value='" + obj.provincia + "'>"+ obj.provincia +"</option>");
			});
			
		});
		
	});
	
	
	$("#id_provincia").change(function(){
		
		var var_dep = $("#id_departamento").val();
		var var_pro = $("#id_provincia").val();
		
		$("#id_distrito").empty();
		$("#id_distrito").append("<option value=' '>[Seleccione Distrito]</option>");

		
		$.getJSON("cargaUbigeo",{"departamento":var_dep,"provincia":var_pro}, function(data){
			
			$.each(data, function(i, obj){
				$("#id_distrito").append("<option value='" + obj.idDistrito + "'>"+ obj.distrito +"</option>");
			});
			
		});
		
	});
		

</script>


</body>
</html>




