module.exports = {

context : __dirname,      // 모듈들이 존재하는 기준 경로 (필수는 아님 없다면 매번 entry 에 풀경로를 적어줘야함)

entry : './src/main.js',    // 엔트리 파일 위치.

output : {                       // output의 엔트리가 배열이면 차례대로 엔트리가 만들어짐

path : __dirname + '../../../src/main/resources/static', // 번들 파일의 대상 경로

filename : 'bundle.js'        // 번들 파일의 이름

},



module : {

	loaders : [

	{

		test : /\.json$/,

		loader : "json-loader"

	},

	{

		test : /\.js$/,

		exclude : /node_modules/,

		loader : "babel-loader",

		query : {

			presets : ['env','react']

		}
	}
	,
	{

			test : /\.css$/,

			loader : "style-loader!css-loader" //postcss 로더 추가

	},
	
	{ test: /\.(woff2?|svg|jpe?g|png|gif|ico)$/, loader: 'file-loader' }

]

},




}
