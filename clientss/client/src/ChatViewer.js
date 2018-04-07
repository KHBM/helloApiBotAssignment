import * as React from 'react';
import './App.css';
import $ from 'jquery'


class ChatViewer extends React.Component {

  render() {
      const {chats, name} = this.props.value;
      return (
          <div>
            <b>{name}</b> &nbsp;&nbsp;&nbsp;
            {chats}
          </div>
      );
  }
}

export default ChatViewer;
