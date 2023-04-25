chrome.action.onClicked.addListener((tab) => {
    chrome.tabs.create({ url: './client/index.html', selected: true, active: true });
  });
  