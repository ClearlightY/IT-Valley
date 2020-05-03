const ap = new APlayer({
  container: document.getElementById('aplayer'),
  fixed: true,
  autoplay: false,
  loop: 'all',
  theme: 'red',
  order: 'list',
  preload: 'auto',
  theme: '#8B275F',
  mutex: true,
  volume: '0.65',
  audio: [
    {
      name: 'Air',
      artist: '纯音乐',
      url: 'https://m7.music.126.net/20200504034747/0232e4cb59ba6a760e0166e8f10be9ad/ymusic/d7c9/4712/e5f2/4b216c4651baecb58df878211f961d91.mp3',
      cover: 'https://p3.music.126.net/_BqzzgcXVksCJkL4-sIxcw==/2892815093153065.jpg?param=300y300',
    },
    {
      name: '2---------------------',
      artist: '--------------------------------------------------------------',
      url: 'http://www.clearlight.top/homepage/music/moshang.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pI7En.png',
    },
    {
      name: '3---------------------',
      artist: '--------------------------------------------------------------',
      url: 'http://www.0dutv.com/plug/down/up2.php/127049945.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pI7En.png',
    },
    {
      name: '4---------------------',
      artist: '--------------------------------------------------------------',
      url: 'https://www.0dutv.com/plug/down/up2.php/651386.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pI7En.png',
    },
    {
      name: '5---------------------',
      artist: '--------------------------------------------------------------',
      url: 'http://www.0dutv.com/plug/down/up2.php/651386.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pI7En.png',
    },
    {
      name: '6---------------------',
      artist: '--------------------------------------------------------------',
      url: 'http://www.0dutv.com/plug/down/up2.php/460876.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pI7En.png',
    },
    {
      name: '7---------------------',
      artist: '--------------------------------------------------------------',
      url: 'http://www.0dutv.com/plug/down/up2.php/818067.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pI7En.png',
    },
    {
      name: '8---------------------',
      artist: '--------------------------------------------------------------',
      url: 'https://www.0dutv.com/plug/down/up2.php/460866.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pI7En.png',
    },
    {
      name: '9---------------------',
      artist: '--------------------------------------------------------------',
      url: 'http://www.0dutv.com/plug/down/up2.php/4830771.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pI7En.png',
    },
    {
      name: '10---------------------',
      artist: '--------------------------------------------------------------',
      url: 'http://www.0dutv.com/plug/down/up2.php/107770314.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pI7En.png',
    },
    {
      name: '11---------------------',
      artist: '--------------------------------------------------------------',
      url: 'http://www.0dutv.com/plug/down/up2.php/107587533.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pI7En.png',
    },
    {
      name: '邱有句',
      artist: '夏天的秘密',
      url: 'http://fdfs.xmcdn.com/group20/M07/24/44/wKgJLFs2-_OCFQYuABlJQt4DYWc894.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg',
    },
    {
      name: '本兮',
      artist: '一直在等',
      url: 'http://fdfs.xmcdn.com/group16/M0B/5C/88/wKgDalXLWD7S_1FIAB8AgG5wQr4608.mp3',
      cover: 'http://imagev2.xmcdn.com/group12/M07/44/29/wKgDW1Wl5rTCWmIlAABU9OJHURY156.jpg!op_type=3&columns=640&rows=640',
    },
    {
      name: '李志',
      artist: '天空之城',
      url: 'http://www.clearlight.top/homepage/music/love.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '蓝色的海',
      url: 'http://www.clearlight.top/homepage/music/blue.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '听妈妈的话',
      url: 'http://www.clearlight.top/homepage/music/mather.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '怎么办我爱你',
      url: 'http://www.clearlight.top/homepage/music/missingyou.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '夜曲',
      url: 'http://www.clearlight.top/homepage/music/night.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '红尘客栈',
      url: 'http://www.clearlight.top/homepage/music/sleep.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '彩虹',
      url: 'http://www.clearlight.top/homepage/music/rainbow.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '稻香',
      url: 'http://www.clearlight.top/homepage/music/xu.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '后来的我们',
      url: 'http://www.clearlight.top/homepage/music/after.flac',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '忘记时间',
      url: 'http://www.clearlight.top/homepage/music/forget.flac',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '小宇',
      url: 'http://www.clearlight.top/homepage/music/small.flac',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '昨日青空',
      url: 'http://www.clearlight.top/homepage/music/yesterday.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '海阔天空',
      url: 'http://www.clearlight.top/homepage/music/haikuo.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: '以冬',
      artist: '我的一个道姑朋友',
      url: 'http://www.clearlight.top/homepage/music/daogu.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '广场',
      url: 'http://www.clearlight.top/homepage/music/guangchang.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '你离开了南京，从此没有人和我说话',
      url: 'http://www.clearlight.top/homepage/music/nanjing.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '忽然',
      url: 'http://www.clearlight.top/homepage/music/huran.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '结婚',
      url: 'http://www.clearlight.top/homepage/music/jiehun.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '山阴路的夏天',
      url: 'http://www.clearlight.top/homepage/music/shanyin.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },
    {
      name: ' ',
      artist: '男孩',
      url: 'http://www.clearlight.top/homepage/music/nanhai.flac',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: '李志',
      artist: '梵高先生',
      url: 'http://www.clearlight.top/homepage/music/fangao.flac',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: '沈虫虫/丫蛋蛋',
      artist: '遇见',
      url: 'http://www.clearlight.top/homepage/music/yujian.flac',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: '邱有句',
      artist: '别哭',
      url: 'http://www.clearlight.top/homepage/music/bieku.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: ' ',
      artist: '童话镇',
      url: 'http://www.clearlight.top/homepage/music/tonghua.flac',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: '陶峻汐',
      artist: '和解',
      url: 'http://www.clearlight.top/homepage/music/hejie.flac',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: '陶峻汐',
      artist: '我想你了',
      url: 'http://www.clearlight.top/homepage/music/woxiangnile.flac',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: '赵海洋',
      artist: '秋的思念',
      url: 'http://www.clearlight.top/homepage/music/qiude.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: '杨丞琳',
      artist: '年轮说',
      url: 'http://www.clearlight.top/homepage/music/nianlun.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: ' ',
      artist: '热河',
      url: 'http://www.clearlight.top/homepage/music/rehe.flac',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: ' ',
      artist: '这个世界会好吗',
      url: 'http://www.clearlight.top/homepage/music/world.flac',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: ' ',
      artist: '关于郑州的记忆',
      url: 'http://www.clearlight.top/homepage/music/zhengzhou.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: ' ',
      artist: '人民不需要自由（现场）',
      url: 'http://www.clearlight.top/homepage/music/freenow.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    },{
      name: ' ',
      artist: '人民不需要自由',
      url: 'http://www.clearlight.top/homepage/music/free2.mp3',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    }
    /*
    ,{
      name: ' ',
      artist: '',
      url: 'http://www.clearlight.top/homepage/music/',
      cover: 'https://s2.ax1x.com/2020/03/09/8pTuyF.jpg'
    }
    */

  ]
});
