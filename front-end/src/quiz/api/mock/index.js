/**
 * Created by dora on 2017/9/29.
 */
import MockAdapter from 'axios-mock-adapter'
const _paper = require('./_paper.json')

export default function(axios) {
    const mock = new MockAdapter(axios)

    mock.reset()

    // mock.onAny().reply(200, tags);
    mock.onGet(/.*\/paper/).reply(200, _paper)
    // mock.onGet(/.*\/tag\/all/).reply(200, tag)
}
