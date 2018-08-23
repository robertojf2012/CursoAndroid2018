const args = process.argv;
var result = 0;

switch(args[3].toLowerCase()){

	case 'mas':
		var number1 = parseInt(args[2]);
		var number2 = parseInt(args[4]);
		result = number1+number2;
		console.log("Resultado: "+result);
	break;

	case 'menos':
		var number1 = parseInt(args[2]);
		var number2 = parseInt(args[4]);
		result = number1-number2;
		console.log("Resultado: "+result);
	break;

	case 'por':
	var number1 = parseInt(args[2]);
	var number2 = parseInt(args[4]);
	result = number1*number2;
	console.log("Resultado: "+result);
	break;

	case 'entre':
		var number1 = parseInt(args[2]);
		var number2 = parseInt(args[4]);
		result = number1/number2;
		console.log("Resultado: "+result);
	break;

	default: console.log("Ingrese operador correcto");

}


