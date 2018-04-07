import * as React from 'react';
import './App.css';
import $ from 'jquery'
const card = require('./card.png');

class ChoiceViewer extends React.Component {

	

  render() {
      const {type, choices} = this.props.value;
  
	  if(type == 'NONE') {
		  return (
			  <div>
				<button onClick={this.props.requestActions}> action </button>
				<input id="randomText" type="input" />
				<button onClick={(e) => {this.props.requestFreeChat(e, $('#randomText').val()); $('#randomText').val('')}}>send</button>
			  </div>
		  );
	  } else if(type == 'BUTTON') {
		  return (
			  <div>
				{
				  choices.map((choice) => 
						<div key={choice.choiceId}>
							<button onClick={(e) => this.props.requestNextContent(e, choice.contentId, choice.choiceId, choice.choiceId)}>{choice.choiceText}</button>
						</div>
				  )
				}
			  </div>
		  );
	  }	else if(type == 'NAME') {
		return (
			  <div>
				<input id="nickname" type="input" />
				<button onClick={(e) => this.props.requestNextContent(e, choices[0].contentId, choices[0].choiceId, $('#nickname').val())}>send</button>
			  </div>
		  );
	  } else if(type == 'CARD') {
		  var style_img = {width:"50px"};
		  return (
				<div>
				{
				  choices.map((choice) => 
						<div key={choice.choiceId}>
							<img src={card} style={style_img} onClick={(e) => this.props.requestNextContent(e, choice.contentId, choice.choiceId, choice.choiceId)}/>CARD {choice.choiceText}
						</div>
				  )
				}
				</div>
		  );
	  } else if(type == 'ACTION') {
		  return (
			  <div>
			  {
				  choices.map((choice) => 
						<div key={choice.actionId}>
							<button onClick={(e) => this.props.requestContent(e, choice.actionId)}>{choice.actionName}</button>
						</div>
				  )
			  }
			  </div>
		  );
	  } else if(type == 'TEXT') {
		  return (
			  <div>
				<button onClick={(e) => {this.props.preventTalkingFromStop()}}> action </button>
				<input id="textQuestion" type="input" />
				<button onClick={(e) => {this.props.requestNextContent(e, choices[0].contentId, choices[0].choiceId, $('#textQuestion').val()); $('#textQuestion').val('')}}>send</button>
			  </div>
		  );
	  }
  }
}

export default ChoiceViewer;
