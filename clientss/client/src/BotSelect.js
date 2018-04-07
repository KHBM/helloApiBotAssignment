import * as React from 'react';
import './App.css';
import $ from 'jquery'

class BotSelect extends React.Component {

  constructor(props) {
    super(props);
  }
  
  render() {
	  if(this.props.isVisible) {
		  console.log(this.props);
		  const {bots, isLoading} = this.props.value;
		  if (isLoading) {
			return <p>Loading...</p>;
		  }
		  return (
			  <div>
				<h2>Choose Bot!</h2>
				{bots.map((bot: any) =>
				  <div key={bot.botId} data-val={bot.botId} onClick={this.props.onclickm}>
					{bot.botName}
				  </div>
				)}
			  </div>
		  );
	  } else {
		  return <div></div>;
	  }
  }
}

export default BotSelect;
