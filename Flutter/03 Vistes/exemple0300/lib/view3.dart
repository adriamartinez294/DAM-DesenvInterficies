import 'package:flutter/cupertino.dart';

class View3 extends StatelessWidget {
  const View3({super.key});

  @override
  Widget build(BuildContext context) {
    final double topPadding =
        MediaQuery.of(context).padding.top + kMinInteractiveDimensionCupertino;

    return CupertinoPageScaffold(
      navigationBar: const CupertinoNavigationBar(
        middle: Text('View 3'),
      ),
      child: Column(
        children: <Widget>[
          SizedBox(height: topPadding),
          const Padding(
            padding: EdgeInsets.all(16.0),
            child: Text('This is view 3', style: TextStyle(fontSize: 24)),
          ),
          const Expanded(
            child: Center(
              child: Text('🥳', style: TextStyle(fontSize: 64)),
            ),
          ),
        ],
      ),
    );
  }
}