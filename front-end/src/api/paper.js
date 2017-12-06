const _paper = {
    problems: [
        {
            id: '1',
            title: 'problem 1',
            options: [
                {
                    id: '1',
                    content: 'option 1',
                    checked: false
                },
                {
                    id: '2',
                    content: 'option 2',
                    checked: false
                },
                {
                    id: '3',
                    content: 'option 3',
                    checked: false
                },
                {
                    id: '4',
                    content: 'option 4',
                    checked: false
                }
            ]
        },
        {
            id: '2',
            title: 'problem 2',
            options: [
                {
                    id: '1',
                    content: 'option 1',
                    checked: false
                },
                {
                    id: '2',
                    content: 'option 2',
                    checked: false
                },
                {
                    id: '3',
                    content: 'option 3',
                    checked: false
                },
                {
                    id: '4',
                    content: 'option 4',
                    checked: false
                }
            ]
        },
        {
            id: '3',
            title: 'problem 3',
            options: [
                {
                    id: '1',
                    content: 'option 1',
                    checked: false
                },
                {
                    id: '2',
                    content: 'option 2',
                    checked: false
                },
                {
                    id: '3',
                    content: 'option 3',
                    checked: false
                },
                {
                    id: '4',
                    content: 'option 4',
                    checked: false
                }
            ]
        }
    ]
}

export default {
    getOne(cb) {
        setTimeout(() => cb(_paper), 100)
    }
}
