# react-native-color-manager

##### Library for managing the native colors of an Android app.

[![npm version](https://img.shields.io/npm/v/react-native-color-manager.svg?style=flat-square)](https://www.npmjs.com/package/react-native-color-manager)
[![npm downloads](https://img.shields.io/npm/dw/react-native-color-manager.svg?style=flat-square)](https://www.npmjs.com/package/react-native-color-manager)
[![npm license](https://img.shields.io/npm/l/react-native-color-manager.svg?style=flat-square)](https://www.npmjs.com/package/react-native-color-manager)

---

# Instalation

##### To install `react-native-color-manager` - you can use `npm` or `yarn` package manager.

```bash
npm install react-native-color-manager --save
react-native link react-native-color-manager
```

or

```bash
yarn add react-native-color-manager
react-native link react-native-color-manager
```

---

# Documentation

The `react-native-color-manager` library includes the `ColorManager` object interface that has the next methods: `setStatusBarColor`, `setNavigationBarColor`, `setRecentColor`. Below you can find the documentation for all methods.

###### `Important! All colors should be provided in the hex format.`

---

## The `setStatusBarColor` method

##### Method that change the status bar color.

##### The `setStatusBarColor` method accept next arguments:

| Name     | Type    | Default value | Required |
| -------- | ------- | ------------- | -------- |
| color    | string  |               | Yes      |
| animated | boolean | false         | No       |
| duration | number  | 300           | No       |

---

## The `setNavigationBarColor` method

##### Method that change the navigation bar color.

##### The `setNavigationBarColor` method accept next arguments:

| Name     | Type    | Default value | Required |
| -------- | ------- | ------------- | -------- |
| color    | string  |               | Yes      |
| animated | boolean | false         | No       |
| duration | number  | 300           | No       |

---

## The `setRecentColor` method

##### Method that change the recent color.

##### The `setRecentColor` method accept next arguments:

| Name  | Type   | Default value | Required |
| ----- | ------ | ------------- | -------- |
| color | string |               | Yes      |

---

# Examples

```js
  import React, { Component } from 'react';
  import { ColorManager } from 'react-native-color-manager';

  class App extends Component {

    componentDidMount() {
      const needAnimation = true;

      if (needAnimation) {
        ColorManager.setStatusBarColor('#2196F3', true, 300);
        ColorManager.setNavigationBarColor('#2196F3', true, 300);
        ColorManager.setRecentColor('#2196F3');
      } else {
        ColorManager.setStatusBarColor('#2196F3');
        ColorManager.setNavigationBarColor('#2196F3');
        ColorManager.setRecentColor('#2196F3');
      }
    }

    render() {
      return null;
    }
  }
```

---
