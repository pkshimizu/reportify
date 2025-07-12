# Reportify - Compose for Desktop アプリケーション

Reportifyは、KotlinとCompose for Desktopを使用したデスクトップアプリケーションです。

## 機能

- 🎨 モダンなMaterial Design 3 UI
- 📱 レスポンシブレイアウト
- 🔧 Kotlinによる型安全な開発
- 🚀 高速なネイティブ実行

## 技術スタック

- **Kotlin** - プログラミング言語
- **Compose for Desktop** - UIフレームワーク
- **Material Design 3** - デザインシステム
- **Gradle** - ビルドツール

## セットアップ

### 前提条件

- JDK 17以上
- Gradle 8.4以上

### インストール

1. リポジトリをクローン
```bash
git clone <repository-url>
cd reportify
```

2. アプリケーションを実行
```bash
./gradlew run
```

### ビルド

```bash
# アプリケーションをビルド
./gradlew build

# ネイティブディストリビューションを作成
./gradlew createDistributable

# macOS用アプリバンドルを作成
./gradlew packageDmg

# Windows用インストーラーを作成
./gradlew packageMsi

# Linux用パッケージを作成
./gradlew packageDeb
```

## プロジェクト構造

```
reportify/
├── src/main/kotlin/net/noncore/reportify/
│   └── Main.kt              # メインアプリケーション
├── build.gradle.kts         # ビルド設定
├── settings.gradle.kts      # プロジェクト設定
├── gradle.properties        # Gradleプロパティ
└── README.md               # プロジェクト説明
```

## 使用方法

アプリケーションを起動すると、シンプルなHello Worldメッセージが表示されます。

## 開発ガイド

### 新しい機能の追加

1. **UIコンポーネント**: `src/main/kotlin/net/noncore/reportify/` に新しいComposable関数を追加
2. **状態管理**: `@Composable` 関数内で状態を管理
3. **ナビゲーション**: Compose Navigationを使用

### デザインシステム

Material Design 3を使用しており、以下のコンポーネントが利用可能です：

- `MaterialTheme` - テーマ設定
- `Surface` - コンテナ
- `Text` - テキスト表示
- `Button` - ボタン
- `TextField` - テキスト入力
- `Card` - カード

### プラットフォーム対応

- **macOS**: DMGファイルで配布
- **Windows**: MSIインストーラーで配布
- **Linux**: DEBパッケージで配布

## トラブルシューティング

### よくある問題

1. **JDKバージョンエラー**
   - JDK 17以上がインストールされているか確認
   - `java -version` でバージョンを確認

2. **Gradleビルドエラー**
   - `./gradlew clean` でキャッシュをクリア
   - 依存関係のバージョンを確認

3. **Compose for Desktopエラー**
   - Composeプラグインのバージョンを確認
   - 依存関係の競合を解決

## ライセンス

MIT License

## 貢献

1. フォークを作成
2. フィーチャーブランチを作成 (`git checkout -b feature/amazing-feature`)
3. 変更をコミット (`git commit -m 'Add amazing feature'`)
4. ブランチにプッシュ (`git push origin feature/amazing-feature`)
5. プルリクエストを作成

## 更新履歴

### v1.0.0
- 初期リリース
- Hello Worldアプリケーション
- Material Design 3対応
- マルチプラットフォーム対応

## サポート

問題や質問がある場合は、GitHubのIssuesページでお知らせください。 