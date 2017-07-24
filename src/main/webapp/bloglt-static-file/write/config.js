/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function (config) {
    // Define changes to default configuration here. For example:
    // config.language = 'fr';
    // config.uiColor = '#AADC6E';

    config.height = 500;

    config.removeButtons = 'About,Print,Language,BidiRtl,BidiLtr';
    //表情图片源 ，CKEDITOR.basePath指http://localhost:8080/.......
    smiley_path : CKEDITOR.basePath + 'plugins/smiley/images/';
    config.smiley_images = [
        't_0001.gif',
        't_0002.gif',
        't_0003.gif',
        't_0004.gif',
        't_0005.gif',
        't_0006.gif',
        't_0007.gif',
        't_0008.gif',
        't_0009.gif',
        't_0010.gif',
        't_0011.gif',
        't_0012.gif',
        't_0013.gif',
        't_0014.gif',
        't_0015.gif',
        't_0016.gif',
        't_0017.gif',
        't_0018.gif',
        't_0019.gif',
        't_0020.gif',
        't_0021.gif',
        't_0022.gif',
        't_0023.gif',
        't_0024.gif',
        't_0025.gif',
        't_0026.gif',
        't_0027.gif',
        't_0028.gif',
        't_0029.gif',
        't_0030.gif',
        't_0031.gif',
        't_0032.gif',
        't_0033.gif',
        't_0034.gif',
        't_0035.gif',
        't_0036.gif',
        't_0037.gif',
        't_0038.gif',
        't_0039.gif',
        't_0040.gif'
    ];

};
