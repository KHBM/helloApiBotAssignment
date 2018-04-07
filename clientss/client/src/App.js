import * as React from 'react';
import './App.css';
import BotSelect from './BotSelect';
import ChatViewer from './ChatViewer';
import ChoiceViewer from './ChoiceViewer';
import $ from 'jquery'

const logo = require('./ninja.png');


class App extends React.Component {

  constructor(props: any) {
    super(props);

    this.state = {
		bots: {
			bots: [],
			isLoading: false
		},
		chats: [],
		isVisible: true,
		botId:"-1",
		botName:null,
		choices:null,
		type:'NONE'
    };
  }

  componentDidMount() {
    this.setState({bots:{isLoading: true}});

    fetch('http://localhost:3333/bots')
    .then(response => response.json())
    .then(data => this.setState({bots:{bots: data, isLoading: false}}));
  }

  getActionss(e) {
	  fetch('http://localhost:3333/actions/'+this.state.botId)
		 .then(rep => rep.json())
		 .then(data => {
			 console.log(data);
			 var newState = Object.assign({}, this.state, {});
			 newState.choices = data;
			 newState.type = 'ACTION';
			 this.setState(newState);
		 });
  }
  
  keepTalking(e) {
		 var newState = Object.assign({}, this.state, {});
		 newState.chats.push(
			 {
				 name:newState.botName,
				 chats:"하던 이야기 마저 하자!"
			 });
		 this.setState(newState);
  }
  
  getContents(e, actionId) {
	  console.log("get content called");
	  
	  fetch('http://localhost:3333/content/'+this.state.botId +"/"+actionId)
		 .then(rep => rep.json())
		 .then(data => {
			 console.log(data);
			 var newState = Object.assign({}, this.state, {});
			 newState.type=data.nextChoiceType;
			 newState.choices = data.choices;
			 newState.chats.push(
			 {
				 name:newState.botName,
				 chats:data.contentString
			 }
			 );
			 this.setState(newState);
		}).catch(e => {
			var newState = Object.assign({}, this.state, {})
			newState.type='NONE';
			 newState.choices = [];
			newState.chats.push({
						chats:"준비가 덜 되었어",
						name:newState.botName
					  });
			this.setState(newState);
		});
  }
  
  getNextContents(e, contentId, choiceId, data) {
	  console.log("get next content called");
	  console.log("RE", this.state);
	  console.log("get action called "+this.state.botId);
	  fetch('http://localhost:3333/content/'+contentId+'/choice/'+choiceId+'/'+data, {
			  method: 'GET'
			  
		 })
		.then(rep => rep.json()
		)
		.then(datas => {
			console.log(datas);
			var newState = Object.assign({}, this.state, {})
			if(newState.type == 'TEXT' || newState.type == 'NONE') {
			 newState.chats.push(
			 {
				 name:'당신',
				 chats:data
			 });
			} else if(newState.type == 'NAME') {
			 newState.chats.push(
			 {
				 name:'당신',
				 chats:'그 사람은 ' + data + ' 라 부르면 돼'
			 });
			} else if(newState.type == 'BUTTON') {
			 var btnText = null;
			 $.each(newState.choices, (i, e) => {
				console.log(i, e);
				if(e.choiceId == data) {
					btnText = e.choiceText;
				}
			 });
			 newState.chats.push(
			 {
				 name:'당신',
				 chats:btnText
			 });
			}
			 newState.type=datas.nextChoiceType;
			 newState.choices = datas.choices;
			 
			 
			 newState.chats.push(
			 {
				 name:newState.botName,
				 chats:datas.contentString
			 });
			 this.setState(newState);
		})
		.catch(e => {
			var newState = Object.assign({}, this.state, {})
			if(newState.type != 'NAME') {
				newState.chats.push({
						chats:"준비가 덜 되었어",
						name:newState.botName
					  });
			} else {
				newState.chats.push({
						chats:"닉네임을 입력해줘",
						name:newState.botName
					  });
			}
			this.setState(newState);
		});
  }
  
  getRandomChat(e, data) {
	  fetch('http://localhost:3333/chat/'+data)
	  .then(rep => rep.text())
	  .then(datas => {
		 console.log(data); 
		 var newState = Object.assign({}, this.state, {});
		 newState.chats.push(
		 {
			 name:'당신',
			 chats:data
		 });
		 newState.chats.push({
						chats:datas,
						name:newState.botName
					  });
		 newState.type = 'NONE';
		 this.setState(newState);
	  });
  }
  
  onBotClicked(e: Event) {
	  console.log(e.target);
	  const botId = $(e.target).attr('data-val');
	  this.setState({isVisible:false});
	  
	  fetch('http://localhost:3333/bot/'+botId)
	  .then(rep => rep.json())
	  .then(data => {
		 var newState = Object.assign({}, this.state, {});
		 newState.chats.push({
						chats:data.description,
						name:data.botName
					  });
		 newState.botId = botId;
		 newState.botName = data.botName;
		 newState.type = 'NONE';
		 console.log("OLD", this.state);
		 console.log("NEW", newState);
		 this.setState(newState);
	  }).catch(e => {
		  console.log("error");
	  });
  }
  
  render() {
	  console.log("THIS IS STATE", this.state);
      const chats = this.state.chats;
	  var choicview;
	  if(this.state.isVisible == false) {
			 choicview = (
				 <ChoiceViewer value={{type:this.state.type, choices:this.state.choices}} 
					requestActions={this.getActionss.bind(this)} 
					requestContent={this.getContents.bind(this)} 
					requestNextContent={this.getNextContents.bind(this)} 
					preventTalkingFromStop={this.keepTalking.bind(this)} 
					requestFreeChat={this.getRandomChat.bind(this)} 
				 />
			 )
	  }
      return (
        <div className="App">
          <div className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <h2>환영합니다.</h2>
          </div>
          <BotSelect value={this.state.bots} isVisible={this.state.isVisible}
					onclickm={this.onBotClicked.bind(this)}/>
		
		  {chats.map((chat, i) => {
			  console.log("HHHHHHHHHHHHHhh", chat, i);
				  return (
					<div key={i+chat.chats}>
						<ChatViewer value={chat}/>
					</div>
					);
		  })}
		  {choicview}
        </div>
      );
  }
}

export default App;
