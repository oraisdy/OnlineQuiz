const _paper = {
    problems: [
        {
            id: '1',
            title: 'problem 1',
            options: {
                'option 1': {
                    checked: false
                },
                'option 2': {
                    checked: false
                },
                'option 3': {
                    checked: false
                },
                'option 4': {
                    checked: false
                }
            }
        },
        {
            id: '2',
            title: 'problem 2',
            options: {
                'option 1': {
                    checked: false
                },
                'option 2': {
                    checked: false
                },
                'option 3': {
                    checked: false
                },
                'option 4': {
                    checked: false
                }
            }
        },
        {
            id: '3',
            title: 'problem 3',
            options: {
                'option 1': {
                    checked: false
                },
                'option 2': {
                    checked: false
                },
                'option 3': {
                    checked: false
                },
                'option 4': {
                    checked: false
                }
            }
        }
    ]
}

export default {
    getOne(cb) {
        setTimeout(() => cb(_paper), 100)
    }

    // buyProducts (products, cb, errorCb) {
    //   setTimeout(() => {
    //     // simulate random checkout failure.
    //     (Math.random() > 0.5 || navigator.userAgent.indexOf('PhantomJS') > -1)
    //       ? cb()
    //       : errorCb()
    //   }, 100)
    // }
}
